package com.softisland.middleware.domain.util.page;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页返回
 * @author ss
 * @version 0.1
 */
public class PageResult<T> extends BaseResult {

	private List<T> aaData;
	private long iTotalRecords;
	private long iTotalDisplayRecords;

	public PageResult( List<T> aaData,long iTotalRecords) {
		this.aaData = aaData;
		this.iTotalDisplayRecords = iTotalRecords;
		this.iTotalRecords = iTotalRecords;
	}
	
	public PageResult(PageInfo<T> pageInfo) {
		this.aaData = pageInfo.getList();
		this.iTotalDisplayRecords = pageInfo.getTotal();
		this.iTotalRecords = pageInfo.getTotal();
	}

	public PageResult() {
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}





}
