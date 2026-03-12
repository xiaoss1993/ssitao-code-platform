# 测试数据生成器设计文档

## 1. 需求概述

为项目开发测试数据生成功能，通过 REST API 接口方式生成大量测试数据（每个实体 1000-5000 条）。

## 2. 目标模块

- **IAM 模块**: 用户、角色、权限、菜单、部门、公司、岗位
- **Meta 模块**: 实体、表单、列表、流程等

## 3. 实现方案

### 3.1 技术选型

- **数据生成工具**: 使用 Hutool 的 `RandomUtil`、`IdUtil`、`DateUtil` 生成随机数据
- **调用方式**: 通过业务 Service API 调用生成数据，保证业务逻辑一致性
- **API 接口**: RESTful 风格，支持指定模块和数据量

### 3.2 架构设计

```
┌─────────────────────────────────────────────────────────────┐
│                    TestDataController                       │
│              (API 接口层 - /api/test-data/*)                │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                   TestDataGeneratorService                  │
│                   (测试数据生成服务)                         │
│  - generateIamData(IamDataTypeEnum, int count)              │
│  - generateMetaData(MetaDataTypeEnum, int count)            │
│  - generateAllData(int count)                               │
└─────────────────────────────────────────────────────────────┘
           │                               │
           ▼                               ▼
┌──────────────────────┐    ┌────────────────────────────────┐
│   IAM 模块 Service   │    │      Meta 模块 Service        │
│ (调用现有业务接口)   │    │    (调用现有业务接口)          │
└──────────────────────┘    └────────────────────────────────┘
```

### 3.3 数据生成顺序

为保证数据关联完整性，按以下顺序生成：

1. **公司 (Company)** → 2. **部门 (Department)** → 3. **岗位 (Post)**
2. **角色 (Role)** → 4. **权限 (Permission)** → 5. **菜单 (Menu)**
3. **用户 (User)** → 6. **用户关联(用户-部门、用户-角色、用户-权限)**

### 3.4 接口设计

| 接口路径 | 方法 | 参数 | 说明 |
|---------|------|------|------|
| `/api/test-data/iam/{type}` | POST | type: 数据类型, count: 数量 | 生成IAM模块数据 |
| `/api/test-data/meta/{type}` | POST | type: 数据类型, count: 数量 | 生成Meta模块数据 |
| `/api/test-data/all` | POST | count: 数量 | 生成全部模块数据 |
| `/api/test-data/clear` | DELETE | type: 模块类型 | 清空测试数据 |

### 3.5 数据生成规则

**用户数据:**
- userCode: 随机 UUID 前8位 + 随机数字
- userName: 随机中文姓名
- userPhone: 138/139/150/151 + 8位随机数字
- userMail: 随机字母@random.com
- userSex: MALE/FEMALE/UNKNOWN 随机
- userStatus: ON_JOB/RESIGN/probation 随机
- userEntryDate: 过去3年内随机日期
- 关联部门、角色、权限

**部门数据:**
- 构建树形结构（3层）
- deptTreePath、deptTreeLevel 自动计算
- 随机分配到各公司

**角色/权限/菜单:**
- 随机状态（启用/禁用）
- 随机内置标记
- 权限关联菜单

## 4. 组件列表

| 组件 | 路径 | 说明 |
|------|------|------|
| TestDataController | modular-iam/controller | API 控制器 |
| TestDataGeneratorService | modular-iam/service | 生成服务 |
| IamDataTypeEnum | modular-iam/enums | IAM数据类型枚举 |
| MetaDataTypeEnum | modular-meta/enums | Meta数据类型枚举 |

## 5. 测试验证

- 验证数据生成数量正确
- 验证数据关联完整性（用户-部门-角色）
- 验证 API 响应时间
- 验证大数据量下的系统稳定性
