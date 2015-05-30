package com.rent.web.utils;

import java.util.List;

public class Page<T>{
	private int total_page;		//��ҳ��
    private int total_count;	//�ܼ�¼��
    private int current_page;	//��ǰҳ
    private int page_size;		//ÿҳ������
    private int first_page;
    private int pre_page;
    private int next_page;
    private int last_page;
    private boolean is_first;
    private boolean is_last;
    private List<T> list;

    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
    public int getTotal_page() {
        return total_page;
    }
    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }
    public int getTotal_count() {
        return total_count;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
    public int getCurrent_page() {
        return current_page;
    }
    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }
    public int getPage_size() {
        return page_size;
    }
    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }
    public int getFirst_page() {
        return first_page;
    }
    public void setFirst_page(int first_page) {
        this.first_page = first_page;
    }
    public int getPre_page() {
        return pre_page;
    }
    public void setPre_page(int pre_page) {
        this.pre_page = pre_page;
    }
    public int getNext_page() {
        return next_page;
    }
    public void setNext_page(int next_page) {
        this.next_page = next_page;
    }
    public int getLast_page() {
        return last_page;
    }
    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }
    public boolean isIs_first() {
        return is_first;
    }
    public void setIs_first(boolean is_first) {
        this.is_first = is_first;
    }
    public boolean isIs_last() {
        return is_last;
    }
    public void setIs_last(boolean is_last) {
        this.is_last = is_last;
    }

    /**
     * �����ҳ����
     * @param current_page ��ǰҳ
     * @param page_size ÿҳ������
     * @param total_count �ܼ�¼��
     */
    public  Page(int current_page, int page_size, int total_count){
        this.current_page = current_page;
        this.page_size = page_size;
        this.total_count = total_count;

        if(current_page < 1){
            this.current_page = 1;
        }

        this.total_page = (this.total_count + page_size - 1)/page_size;
        this.first_page =1;
        this.last_page = total_page;

        if(this.current_page > 1){
            this.pre_page = this.current_page - 1;
        }else{
            this.pre_page = 1;
        }

        if(this.current_page < total_page){
            this.next_page = this.current_page + 1;
        }else{
            this.next_page = total_page;
        }

        this.is_first = (this.current_page <= 1);

        this.is_last = (this.current_page >= total_page);
    }

}
