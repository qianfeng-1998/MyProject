package com.qf.bean;

import java.util.List;

public class PageModel<E> {
	private List<E> list; //�����
	private int count; //����������
	private int pageSize=5;	//ҳ����������
	private int pageNo;	//ҳ��
	
	/**
	 * 
	 * @return ��ҳ������������/ÿҳ���� +1
	 */
	public int getTotalPages()
	{
		return (int)Math.ceil(count*1.0/pageSize);
	}
	
	/**
	 *�����ҳ 
	 * @return
	 */
	public int getFirstPage()
	{
		return 1;
		
	}
	
	/**
	 * ��һҳ
	 * @return
	 */
	public int getPreviousPage()
	{
		if (pageNo<=1) {
			return 1;
		}
		return pageNo-1;
	}
	
	/**
	 * ��һҳ
	 * @return
	 */
	public int getNextPage()
	{
		if (pageNo>=getLastPage()) {
			return getLastPage();
		}
		return pageNo+1;
	}
	/**
	 * ���һҳ
	 * @return
	 */
	public int getLastPage()
	{
		return getTotalPages();
	}
	
	public PageModel() {
		
	}
	
	
	public PageModel(List<E> list, int totalRecords, int pageSize, int pageNo) {
		this.list = list;
		this.count = totalRecords;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}


	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	public int getStuCount() {
		return count;
	}
	public void setStuCount(int stuCount) {
		this.count = stuCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

}
