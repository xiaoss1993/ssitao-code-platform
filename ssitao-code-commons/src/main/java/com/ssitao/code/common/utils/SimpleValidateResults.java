package com.ssitao.code.common.utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 简单的验证结果实现
 *
 * @since 3.0
 */
public class SimpleValidateResults implements ValidateResults {

    private final Map<String, List<String>> errors = new LinkedHashMap<>();

    @Override
    public void addResult(String field, String message) {
        errors.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
    }

    @Override
    public List<String> getAllMessages() {
        return errors.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getMessages(String field) {
        return errors.getOrDefault(field, Collections.emptyList());
    }

    @Override
    public Map<String, List<String>> getErrors() {
        return Collections.unmodifiableMap(errors);
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    @Override
    public int getErrorCount() {
        return errors.values().stream().mapToInt(List::size).sum();
    }

    @Override
    public String toString() {
        return "SimpleValidateResults{" +
                "errors=" + errors +
                '}';
    }
}
