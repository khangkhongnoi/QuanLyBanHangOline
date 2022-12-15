package com.YameShop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "invoice_details")
public class Invoice_details {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
			private Long Id_Invoice_Details;
	@Column(name = "amount")
	private Integer Amount;
	@Column(name = "dongia")
	private Integer don_gia;
		@Column(name = "giamgia")
		private Integer giam_gia;
	@Column(name = "total_money")
			private Integer Toatal_Money;
	
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	 @JsonIgnore
	    @JoinColumn(name = "Id_Product_Details")
		Product_details product_details;
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	 @JsonIgnore
	    @JoinColumn(name = "Id_Invoice")
		Invoice invoice;


	public Integer getGiam_gia() {
		return giam_gia;
	}

	public void setGiam_gia(Integer giam_gia) {
		this.giam_gia = giam_gia;
	}

	public Integer getDon_gia() {
		return don_gia;
	}

	public void setDon_gia(Integer don_gia) {
		this.don_gia = don_gia;
	}

	public Long getId_Invoice_Details() {
		return Id_Invoice_Details;
	}

	public void setId_Invoice_Details(Long id_Invoice_Details) {
		Id_Invoice_Details = id_Invoice_Details;
	}

	public Integer getAmount() {
		return Amount;
	}

	public void setAmount(Integer amount) {
		Amount = amount;
	}

	public Integer getToatal_Money() {
		return Toatal_Money;
	}

	public void setToatal_Money(Integer toatal_Money) {
		Toatal_Money = toatal_Money;
	}

	public Product_details getProduct_details() {
		return product_details;
	}

	public void setProduct_details(Product_details product_details) {
		this.product_details = product_details;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	
}
