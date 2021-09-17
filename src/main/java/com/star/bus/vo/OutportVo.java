package com.star.bus.vo;

import java.util.Date;

import com.star.bus.pojo.Outport;
import org.springframework.format.annotation.DateTimeFormat;




public class OutportVo extends Outport {

	private Integer page;  //当前页码

	private Integer limit; //每页显示条数

	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
