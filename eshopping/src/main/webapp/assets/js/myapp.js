$(function() {
	switch(menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	default:
		if(menu=='Home') break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;	
	}
	
	
	// jquery datatable
	// create dataset
	
	
	var $table = $('#productListTable');
	
	if($table.length) {
		//console.log("inside table");
		
		var jsonURL = '';
		if(window.categoryId=='') {
			jsonURL=window.contextRoot+"/json/data/all/products";
		} else {
			jsonURL=window.contextRoot+"/json/data/category/" + window.categoryId + "/products";

		}
		
		$table.DataTable({
			
			ajax :{
				url:jsonURL,
				dataSrc:''
			},
			columns: [
				{
					data:'code',
					mRender:function(data,type,row) {
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
				},
				{
					data:'name'
				},
				{
					data:'brand'
				},
				{
					data:'price',
					mRender:function(data,type,row) {
						return '&#36;' + data;
					}
				},
				{
					data:'quantity',
						mRender:function(data,type,row) {
							if(data<1) {
								return "<span style=color:'red'>Out Of Stock</span>";
							} 
							return data;
						}
				},
				{
					data:'id',
					bSortable:false,
					mRender: function(data,type,row) {
						var str='';
						console.log("data quantity: " + row.quantity);
						if(row.quantity < 1 ) {
							str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"/></a>&#160;';
							str += "<a href='javascript:void(0)'><span class='btn btn-success disabled'><strike><span class='glyphicon glyphicon-shopping-cart'>Add to Cart</span></strike></a>&#160";
						} else {
							str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"/></a>&#160;';
							str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"/></a>&#160;';
						}
						return str;
					}
				}
			]
		});
	}
});