package kr.or.connect.reservation.dto;

public class CategoryItem {
	private Integer count;
	private Integer id;
	private String name;

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Item [count=" + count + ", id=" + id + ", name=" + name + "]";
	}

}
