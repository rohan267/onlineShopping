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
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	default:
		if(menu=='Home') break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;	
	}
	
	
	// jquery datatable
	// create dataset
	
	
	// Tackle CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	
	if(token.length>0 && token.length>0) {
		//set the token header for ajax request
		$(document).ajaxSend(function(e,xhr,options) {
			xhr.setRequestHeader(header,token);
		});
		}
	
	
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
						str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"/></a>&#160;';

						if(userRole=='ADMIN') {
							str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"/></a>&#160;';
						} else {
							if(row.quantity < 1 ) {
								str += "<a href='javascript:void(0)'><span class='btn btn-success disabled'><strike><span class='glyphicon glyphicon-shopping-cart'>Add to Cart</span></strike></a>&#160";
							} else {
								str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"/></a>&#160;';
							}
						}
						return str;
					}
				}
			]
		});
	}
	
	
	var $alert=$('.alert');
	if(alert.length) {
		setTimeout(function(){
			alert.fadeOut('slow');
		}, 5000)
	}
	
	
	
	// admin data table
	var $adminProductTable = $('#adminProductsTable');
	
	if($adminProductTable.length) {
		//console.log("inside table");
		
		var jsonURL = window.contextRoot+'/json/data/admin/all/products';
		
		$adminProductTable.DataTable({
			lengthMenu: [[10,30,50,-1],['10 Records', '30 Records','50 Records','ALL']],
			pageLength:30,
			ajax :{
				url:jsonURL,
				dataSrc:''
			},
			columns: [
				{
					
					data:'id',
				},
				{
					
					data:'code',
					bSortable:false,
					mRender:function(data,type,row) {
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"/>';
					}
				},
				{
					data:'name'
				},
				{
					data:'brand'
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
					data:'price',
					mRender:function(data,type,row) {
						return '&#36;' + data;
					}
				},
				{
					data:'active',
					mRender:function(data,type,row) {
						var str='';
						str+='<label class="switch">';
						
						if(data) {
							str+='<input type="checkbox" checked="checked" value="'+row.id+'"/>';
						} else {
							str+='<input type="checkbox" value="'+row.id+'"/>';
						}
						
						str+='<div class="slider"></div></label>';
						return str;
					},
					bSortable:false
				},
				{
					data:'id',
					bSortable:false,
					mRender:function(data,type,row) {
						var str=''
						str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
						str+='<span class="glyphicon glyphicon-pencil"/></a>';	
							
						return str;
					}
				}
			],
			
			initComplete:function() {
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change',function(){
					var checkbox = $(this);
					var checked=checkbox.prop('checked');
					var dMsg = (checked)?'You want to activate the product?': 'You want to Deactivate the product?';
					var value = checkbox.prop('value');
					
					
					if (confirm(dMsg)) {
						var activationURL = window.contextRoot+'/manage/product/'+value+'/activation';
						$.post(activationURL,function(data){
							alert(data);
						});	
					} else {
						checkbox.prop('checked',!checked);
					}
					
//					bootbox.confirm({
//						
//						size:'medium',
//						title:'Product active status',
//						message:dMsg,
//						callback:function(confirmed) {
//							if(confirmed) {
//								console.log(value);
//								bootbox.alert({
//									size:'medium',
//									title:'information',
//									message:'Product value will be changed' + value
//								})
//							} else {
//								checkbox.prop('checked',!checked);
//							}
//						}
//					})
			})
			}
		});
	}
	
	// jquery validation
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length) {
		
		$categoryForm.validate({
			rules : {
				name: {
					
					required:true,
					minlength:2
				},
				description:{
					required:true,
					
				}
			},
			message:{
				name:{
					required:"Add Category name",
					minlength:"Minimum length 2 is required"
				},
				description:{
					required:"Add Category description",
				}
				
			},
			errorElement:'em',
			errorPlacement:function(error,element) {
				error.addClass('help-block');
				error.insertAfter(element);
			}
		});
	}
	//$('#myCategoryModal').appendTo("body").modal('show');

	
	
	// login validation
var $loginForm = $('#loginForm');
	
	if($loginForm.length) {
		
		$loginForm.validate({
			rules : {
				username: {
					required:true,
					email: true
				},
				password:{
					required:true,
				}
			},
			message:{
				username:{
					required:"User name required",
					email:"Enter valid email address"
				},
				password:{
					required:"Password is required",
				}
			},
			errorElement:'em',
			errorPlacement:function(error,element) {
				error.addClass('help-block');
				error.insertAfter(element);
			}
		});
	}
});