package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.controller.vo.notice.NoticeCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.notice.NoticeListReqVO;
import com.ssitao.code.modular.iam.controller.vo.notice.NoticeRespVO;
import com.ssitao.code.modular.iam.controller.vo.notice.NoticeUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.NoticeDO;
import com.ssitao.code.modular.iam.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 通知公告控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "管理后台 - 通知公告")
@RestController
@RequestMapping("/iam/notice")
@RequiredArgsConstructor
@Validated
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/create")
    @Operation(summary = "创建通知公告")
    public CommonResult<Long> createNotice(@Valid @RequestBody NoticeCreateReqVO createReqVO) {
        return success(noticeService.createNotice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通知公告")
    public CommonResult<Void> updateNotice(@Valid @RequestBody NoticeUpdateReqVO updateReqVO) {
        noticeService.updateNotice(updateReqVO);
        return success();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除通知公告")
    @Parameter(name = "id", description = "公告ID", required = true)
    public CommonResult<Void> deleteNotice(@PathVariable("id") Long id) {
        noticeService.deleteNotice(id);
        return success();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "获取通知公告详情")
    @Parameter(name = "id", description = "公告ID", required = true)
    public CommonResult<NoticeRespVO> getNotice(@PathVariable("id") Long id) {
        NoticeDO notice = noticeService.getNotice(id);
        NoticeRespVO respVO = new NoticeRespVO();
        BeanUtils.copyProperties(notice, respVO);
        return success(respVO);
    }

    @GetMapping("/list")
    @Operation(summary = "获取通知公告列表")
    public CommonResult<List<NoticeRespVO>> getNoticeList(@Valid NoticeListReqVO reqVO) {
        List<NoticeDO> list = noticeService.getNoticeList(reqVO);
        List<NoticeRespVO> respList = list.stream()
                .map(notice -> {
                    NoticeRespVO respVO = new NoticeRespVO();
                    BeanUtils.copyProperties(notice, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());
        return success(respList);
    }

    @PutMapping("/publish/{id}")
    @Operation(summary = "发布通知公告")
    @Parameter(name = "id", description = "公告ID", required = true)
    public CommonResult<Void> publishNotice(@PathVariable("id") Long id) {
        noticeService.publishNotice(id);
        return success();
    }

}
