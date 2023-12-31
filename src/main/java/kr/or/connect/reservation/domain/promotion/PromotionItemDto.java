package kr.or.connect.reservation.domain.promotion;

public class PromotionItemDto {
	private int id;
	private int productId;
	private String productImageUrl;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "PromotionItem [id=" + id + ", productId=" + productId + ", productImageUrl=" + productImageUrl + "]";
	}
}
