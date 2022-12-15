package com.YameShop.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseRatingKey {
			@Column(name = "Id_Invoice")
			Long Id_Invoice;
			@Column(name = "Id_Poroduct")
			Long Id_Poroduct;
			@Column(name = "Id_Size")
			Integer Id_Size;
			@Column(name = "Id_Product_Color")
			Integer Id_Product_Color;
}
