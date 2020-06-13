package fit.se.main.dto;

public class CategoryCreateDTO {
	private int categoryId;
	private String categoryName;
	
	public CategoryCreateDTO(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "CategoryDTO [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	
}
