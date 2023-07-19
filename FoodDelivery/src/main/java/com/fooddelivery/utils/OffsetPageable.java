package com.fooddelivery.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetPageable implements Pageable {

    private int limit;
    private int offset;

    public static OffsetPageable of(int limit, int offset) {
        return new OffsetPageable(limit, offset);
    }

    private OffsetPageable(int limit, int offset) {
        if (limit < 1) {
            limit = 1;
        }
        if (offset < 0) {
            offset = 0;
        }
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return Sort.unsorted();
    }

    @Override
    public Pageable next() {
        return new OffsetPageable(getPageSize(), (int) (getOffset() + getPageSize()));
    }

    public Pageable previous() {
        if (hasPrevious()) {
            return new OffsetPageable(getPageSize(), (int) (getOffset() - getPageSize()));
        }
        return this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetPageable(getPageSize(), 0);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new OffsetPageable(limit, limit * pageNumber);
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}


