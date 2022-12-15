function trahtml(data){
	 var tablecategory =	$("#tablecategory").find("tbody");
		tablecategory.empty();
		
	//tablecategory.append(
	//' <table class="table"> <thead> <tr> <th scope="col">#</th>' 
	//+' <th scope="col">First</th> <th scope="col">Last</th><th scope="col">Handle</th> </tr> </thead><tbody><tr>  <th scope="row">1</th>   <td>Mark</td>  <td>Otto</td> <td>@mdo</td>  </tr <tr><th scope="row">2</th><td>Jacob</td><td>Thornton</td> <td>@fat</td </tr></tbody></table> '		
//	)
		for(var i = 0; i<data.length; i++){
		if(data[i].status === 0){
	tablecategory.append('<tr class="categorys">'
	+ '<td class="IdCategory1"> '+ data[i].id_Category  +' </td>'
	+'<td class="categoryName" id="khangcogi"> ' + data[i].category_Name  +' </td>'
		
		
	+"<td> <span class=\" btn badge bg-secondary\" data-id= " + data[i].id_Category +" data-name= " + data[i].status +"  " 
	
	+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ data[i].status +" >  Active</span>\r\n"
	
	+ "            	</td>"
		
	 +"<td><a  data-id= " + data[i].id_Category +"\r\n"
		+ "	data-name=" + data[i].category_Name +"\r\n"
		+ " onclick=\"btninfoaize(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
		+ "        class=\"btn-sm btn-outline-info \" ><i class=\"fas fa-edit\"></i></a>\r\n"
		
		+ "          		\r\n"
		+ "          			<a data-id= " + data[i].id_Category+"\r\n"
		+ "          				data-name=" + data[i].category_Name +"\r\n"
		+ "          				onclick=\"showmodal(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
		+ "						 class=\"btn-sm btn-outline-danger btnDelete\"><i class=\"fa fa-trash\"></i></a>\r\n"
		+ "          		</td>"
		
			+"</tr>")
		}
		if(data[i].status === 1){
			tablecategory.append('<tr class="categorys">'
					+ '<td class="IdCategory1"> '+ data[i].id_Category  +' </td>'
					+'<td class="categoryName" id="khangcogi"> ' + data[i].category_Name  +' </td>'
						
						
					+"<td> <span class=\" btn badge bg-primary\" data-id= " + data[i].id_Category +" data-name= " + data[i].status +"  " 
					
					+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ data[i].status +" >  Disbale</span>\r\n"
					
					+ "            	</td>"
						+'<td><a 	class="btn-sm btn-outline-info btninfoaize"><i class="fas fa-edit"></i></a> <a class="btn-sm btn-outline-danger btnDelete"><i class="fa fa-trash"></i></a></td>'
						
							+'</tr>')
		}
		}
 }