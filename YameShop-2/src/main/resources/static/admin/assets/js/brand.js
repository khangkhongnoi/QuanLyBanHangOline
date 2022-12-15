function nhanjson(data){
	 var tablecategory =$("#tablecategory").find("tbody");
		tablecategory.empty();
	for(var i = 0; i<data.length; i++){
		if(data[i].status == 0){
			tablecategory.append('<tr class="categorys">'
			+ '<td class="IdCategory1"> '+ data[i].id_Brand +' </td>'
			+'<td class="categoryName" id="khangcogi"> ' + data[i].brand_Name  +' </td>'
			+"<td> <span class=\" btn badge bg-secondary\" data-id= " + data[i].id_Brand +" data-name= " + data[i].status +"  " 
			+ "onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ data[i].status +" >  Active</span> </td>\r\n"
			+"<td><a  data-id= " + data[i].id_Brand +"\r\n"
			+ "	data-name=" + data[i].brand_Name +"\r\n"
			+ " onclick=\"btninfoaize(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
			+ "        class=\"btn-sm btn-outline-info \" ><i class=\"fas fa-edit\"></i></a>\r\n"
			+ "<a data-id= " + data[i].id_Brand+"\r\n"
			+ "data-name=" + data[i].color_Name +"\r\n"
			+ "onclick=\"showmodal(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
			+ "class=\"btn-sm btn-outline-danger btnDelete\"><i class=\"fa fa-trash\"></i></a> </td>\r\n"
			+"</tr>")
		}
		else{
			tablecategory.append('<tr class="categorys">'
			+ '<td class="IdCategory1"> '+ data[i].id_Brand +' </td>'
			+'<td class="categoryName" id="khangcogi"> ' + data[i].brand_Name  +' </td>'
			+"<td> <span class=\" btn badge bg-primary\" data-id= " + data[i].id_Brand +" data-name= " + data[i].status +"  " 
			+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ data[i].status +" >  Disbale</span> </td>\r\n"
			+"<td><a  data-id= " + data[i].id_Brand +"\r\n"
			+ "	data-name=" + data[i].brand_Name +"\r\n"
			+ " onclick=\"btninfoaize(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
			+ "        class=\"btn-sm btn-outline-info \" ><i class=\"fas fa-edit\"></i></a>\r\n"
			+ "<a data-id= " + data[i].id_Brand+"\r\n"
			+ "data-name=" + data[i].brand_Name +"\r\n"
			+ "onclick=\"showmodal(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
			+ "class=\"btn-sm btn-outline-danger btnDelete\"><i class=\"fa fa-trash\"></i></a> </td>\r\n"
			+"</tr>")
		}
	}
}
