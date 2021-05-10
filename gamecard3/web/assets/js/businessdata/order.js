Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"orderNo"},
		        {name:"orderTotalPrice"},
		        {name:"discountTotalPrice"},
		        {name:"discountPrice"},
		        {name:"zipCode"},
		        {name:"country"},
		        {name:"countryName"},
		        {name:"state"},
		        {name:"city"},
		        {name:"street1"},
		        {name:"street2"},
		        {name:"phoneNum"},
		        {name:"firstName"},
		        {name:"lastName"},
		        {name:"email"},
		        {name:"shippingCalculationMode"},
		        {name:"shippingOptionAmount"},
		        {name:"shippingOptionName"},
		        {name:"orderStatus"},
		        {name:"sessionId"},
		        {name:"memberId"},
		        {name:"memberName"},
		        {name:"transactionId"},
		        {name:"grossAmount"},
		        {name:"currencyId"},
		        {name:"createTm"},
		        {name:"payTm"},
		        {name:"shippingNo"},
		        {name:"dispatchTm"},
		        {name:"dispatchBee"},
		        {name:"dispatchKind"},
		        {name:"refundApplyTm"},
		        {name:"refundApplyDesc"},
		        {name:"declinedTm"},
		        {name:"declinedDesc"},
		        {name:"cancelledTm"},
		        {name:"completedTm"},
		        {name:"addressLog"},
		        {name:"memberMessage"}
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	
	var queryForm = new Ext.form.FormPanel({
		frame:true,
		region:"north",
 	    collapsible:true,
		autoHeight:true,
		items: [

			new Ext.form.FieldSet({
				labelWidth:80,
				autoHeight:true,
				layout:"column",
				title:"查询条件",
				items:[
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:150,
									name:"orderNo",
									fieldLabel:"订单号"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"combo",
									hiddenName:"orderStatus",
									emptyText : '全部',
									store:new Ext.data.SimpleStore({data: [['','全部'],['1','未付款'],['2','已付款'],['3','已发货'],['4','已完结'],['5','取消'],['6','申请退款'],['7','退款'],['8','拒绝退款'],['9','退款审核通过']], fields: ['key','value']}),
									valueField:"key",
									displayField:"value",
									width:150,
									triggerAction:"all",
									fieldLabel:"状态",
									editable:false,
									mode:"local"
							      	}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:150,
									name:"email",
									fieldLabel:"邮箱"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"createTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"创建时间（开始）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"createTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"创建时间（结束）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"payTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"付款时间（开始）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"payTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"付款时间（结束）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"dispatchTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"发货时间（开始）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"dispatchTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"发货时间（结束）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"completedTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"完成时间（开始）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"completedTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"完成时间（结束）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"cancelledTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"取消时间（开始）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"cancelledTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"取消时间（结束）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"refundApplyTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"申请退款时间（开始）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"refundApplyTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"申请退款时间（结束）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"declinedTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"拒绝退款时间（开始）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"declinedTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"拒绝退款时间（结束）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"refundedTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"退款时间（开始）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"refundedTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"退款时间（结束）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"auditTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"退款审核通过时间（开始）"
 									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"auditTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"退款审核通过时间（结束）"
 									}]
						}
						
						
						
				     ]
				})
	]});
	
	var pagingBar = new Ext.PagingToolbar({
		displayInfo:true,
		displayMsg:"当前显示 {0} - {1} 总记录数目 {2}",
		store:gridStore,
		pageSize:pageSize,
		emptyMsg:"未检索到数据"});
	var checkboxSelectionMode  = new Ext.grid.CheckboxSelectionModel({});
	var gridPanel = new Ext.grid.GridPanel(
			{
			region:"center",
			tbar:pagingBar,
			store:gridStore,
			sm: checkboxSelectionMode,
			//stripeRows : true,
			cm: new Ext.grid.ColumnModel([
			                              checkboxSelectionMode,
			                              {header:"orderid",width:80,dataIndex:"id"},
			                              {header:"订单号",width:150,dataIndex:"orderNo"},
			                              {header:"订单状态",renderer:orderStatusRenderer,dataIndex:"orderStatus"},
			                              {header:"交易完成时间",width:150,renderer:datetimeRenderer,dataIndex:"completedTm"},
			                              {header:"订单取消时间",width:150,renderer:datetimeRenderer,dataIndex:"cancelledTm"},
			                              {header:"拒绝时间",width:150,renderer:datetimeRenderer,dataIndex:"declinedTm"},
			                              {header:"拒绝原因",width:150,dataIndex:"declinedDesc"},
			                              {header:"申请退款时间",width:150,renderer:datetimeRenderer,dataIndex:"refundApplyTm"},
			                              {header:"退款原因",width:150,dataIndex:"refundApplyDesc"},
			                              {header:"发货物流编号",dataIndex:"shippingNo"},
			                              {header:"发货时间",width:150,renderer:datetimeRenderer,dataIndex:"dispatchTm"},
			                              {header:"发货费用",dataIndex:"dispatchBee"},
			                              {header:"发货物流",dataIndex:"dispatchKind"},
			                              {header:"订单总金额",dataIndex:"orderTotalPrice"},
			                              {header:"折后订单总金额",dataIndex:"discountTotalPrice"},
			                              {header:"优惠金额",dataIndex:"discountPrice"},
			                              {header:"paypal交易号",dataIndex:"transactionId"},
			                              {header:"paypal交易金额",dataIndex:"grossAmount"},
			                              {header:"交易货币种类",dataIndex:"currencyId"},
			                              {header:"订单生成时间",width:150,renderer:datetimeRenderer,dataIndex:"createTm"},
			                              {header:"paypal付款时间",width:150,renderer:datetimeRenderer,dataIndex:"payTm"},
			                              {header:"用户选择的物流运费(USD)",width:150,dataIndex:"shippingOptionAmount"},
			                              {header:"用户选择的物流",dataIndex:"shippingOptionName"},
			                              {header:"zip/code",dataIndex:"zipCode"},
			                              {header:"国家代码",dataIndex:"country"},
			                              {header:"国家",dataIndex:"countryName"},
			                              {header:"省/市",dataIndex:"state"},
			                              {header:"城市",dataIndex:"city"},
			                              {header:"地址1",dataIndex:"street1"},
			                              {header:"地址2",dataIndex:"street2"},
			                              {header:"联系方式",dataIndex:"phoneNum"},
			                              {header:"姓",dataIndex:"firstName"},
			                              {header:"名",dataIndex:"lastName"},
			                              {header:"邮箱",dataIndex:"email"},
			                              {header:"系统默认物流（供参考）",width:150,dataIndex:"shippingCalculationMode"},
			                              {header:"sessionid",dataIndex:"sessionId"},
			                              {header:"会员",width:200,dataIndex:"memberName"}
			                              ])
	});
	function datetimeRenderer(v){
		var value = v ;
		if(v != null && v != ''){
			value = formatDate(new Date(v.time)) ;
		}
		return value ;
	}
	
	function orderStatusRenderer(v){
		var value ="" ;
		if(v == '1'){
			value = '<font color=\'red\'>未付款</font>' ;
		}else if(v == '2'){
			value = '已付款' ;
		}else if(v == '3'){
			value = '已发货' ;
		}else if(v == '4'){
			value = '已完结' ;
		}else if(v == '5'){
			value = '取消' ;
		}else if(v == '6'){
			value = '申请退款' ;
		}else if(v == '7'){
			value = '已退款' ;
		}else if(v == '8'){
			value = '拒绝退款' ;
		}else if(v == '9'){
			value = '退款审核通过' ;
		}
		return value ;
	}
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnOrderDetail = new Ext.Button({text:"查看订单详情",iconCls:'detail',handler:onOrderDetail }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnDelete,"-",btnOrderDetail],
			layout:"border",
			//bodyBorder:false,
			items:[
			       queryForm,
			       gridPanel
			       ]
		}],
		listeners : {
			afterrender : function(view){
				onSearch() ;
			}
		}
	});
	
	function onSearch(){
		
		if (queryForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return ;
		}
		gridPanel.getStore().baseParams = queryForm.getForm().getValues();
		gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
		gridPanel.getStore().load();
	}
	
	
	//操作类型变量
	
	/**********************查看订单详情*************************/
		var orderDetailGridStore = new Ext.data.JsonStore({
			//autoLoad:true ,
			url : '../orderDetail/listOrderDetailsByOrderId',
			fields : [ {name : 'id'}, 
			           {name : 'orderId'}, 
			           {name : 'orderNo'}, 
			           {name : 'productId'}, 
			           {name : 'productName'}, 
			           {name : 'amount'}, 
			           {name : 'color'}, 
			           {name : 'size'}, 
			           {name : 'unitPrice'}, 
			           {name : 'basePrice'}
			],
			root : 'root'
		});
		var orderDetailcheckboxSelectionMode = new Ext.grid.CheckboxSelectionModel({});
		var orderDetailGridPanel = new Ext.grid.GridPanel({
			frame:true,
			loadMask:true,
			height:200,
			store : orderDetailGridStore,
			sm : orderDetailcheckboxSelectionMode,
			enableHdMenu : false,
			cm : new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
			                				{header : '订单号',width:150,dataIndex : "orderNo",	sortable : false},
			                				{header : '产品编号',width:125 ,dataIndex : "productNo",	sortable : false},
			                				{header : '产品名称',width:300 ,dataIndex : "productName",	sortable : false},
			                				{header : '折后订单单价',dataIndex : "unitPrice",	sortable : false},
			                				{header : '原订单价',dataIndex : "basePrice",	sortable : false},
			                				{header : '件数',dataIndex : "amount",	sortable : false},
			                				{header : '颜色',dataIndex : "color",	sortable : false},
			                				{header : '尺寸',dataIndex : "size",	sortable : false}
			                				])
		});
		var detailForm = new Ext.form.FormPanel({
			frame:true,
			autoHeight:true,
			items: [
				new Ext.form.FieldSet({
					labelWidth:80,
					autoHeight:true,
					layout:"column",
					title:"地址信息(<font color='red'>paypal填写的地址信息仅供参考</font>)",
					items:[{
								xtype : "panel",
								columnWidth : 1,
								labelWidth : 150,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:"textfield",
										width:800,
										name:"address",
										fieldLabel:"买家收货地址"
								}]
							},{
								xtype : "panel",
								columnWidth : 1,
								labelWidth : 150,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:"textfield",
										width:800,
										name:"addressLog",
										fieldLabel:"paypal填写的收货地址"
								}]
							},{
								xtype : "panel",
								columnWidth : 1,
								labelWidth : 150,
								labelAlign : "left",
								layout : "form",
								items : [{
										xtype:"textarea",
										height:80,
										width:800,
										name:"memberMessage",
										fieldLabel:"买家留言信息"
								}]
							}]
					})
		]});
		var orderDetailWin = new Ext.Window({
			autoHeight:true,
			constrainHeader : true,
			width : 1050,
			closeAction : "hide",
			resizable : false,
			isEditing : false,
			plain : true,
			modal : true,
			items :  [detailForm,orderDetailGridPanel]
		});
		
		// 查看详情
		function onOrderDetail() {
			var records = gridPanel.getSelectionModel().getSelections();
			var record = gridPanel.getSelectionModel().getSelected();
			if (records.length == 1) {
				orderDetailWin.setTitle("订单详情");
				orderDetailWin.show();
				orderDetailGridStore.baseParams["orderId"] = record.data.id;
				orderDetailGridStore.load() ;
				

				var street2  =record.data.street2 ;
				if(street2 == null || street2 == ''){
					street2 = "" ;
				}else{
					street2 = ","+street2+" " ;
				}
				var address = record.data.lastName+" "+record.data.firstName+" "+record.data.street1+" "+street2+record.data.city+", "+record.data.state+" "+record.data.zipCode+" "+record.data.countryName ;
				detailForm.getForm().findField("address").setValue(address) ;
				detailForm.getForm().findField("memberMessage").setValue(record.data.memberMessage) ;
				detailForm.getForm().findField("addressLog").setValue(record.data.lastName+" "+record.data.firstName+" "+record.data.addressLog) ;
			} else if (records.length < 1) {
				Ext.Msg.alert("提示","请选择一笔数据行");
			} else if (records.length > 1) {
				Ext.Msg.alert("提示","不能同时操作多条数据");
			}

		}
	
	/*************************************************************/
	function onSaveOrUpdate(){
		if (discountForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		var waitMsg;
		if (operator_type == 'discount') {
			waitMsg = "正在执行...";
			editForm.getForm().url = "discount";
		}
		discountForm.getForm().submit(
						{
							success : showSaveOrUpdateSuccessInfo,
							failure : showFormSubmitFailureInfo,
							waitMsg : waitMsg,
							waitTitle : "请稍后..."
						});
	}
	
	function showSaveOrUpdateSuccessInfo(form, action) {
		if (action.result.msg) {
			Ext.Msg.alert("提示",action.result.msg);
		} else {
			if (operator_type == 'discount') {
				Ext.Msg.alert("提示","操作成功");
				discountWin.hide();
			}
			
			gridStore.reload();
		}
	}
	
	function onDelete(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			//判断状态  只有未付款的才可以删除
			if(record.data.orderStatus != 1){
				Ext.Msg.alert("提示","只有未付款的订单可以删除");
				return ;
			}
			/*
			if(record.data.memberId != ''){
				Ext.Msg.alert("提示","未付款关联会员的订单不能删除");
				return ;
			}*/
			Ext.MessageBox.confirm('提示','确定要删除选中的记录吗', deleteRecord);
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}
		
	}
	// 删除事件请求调用
	function deleteRecord(result) {
			if (result == 'yes') {
				var records = gridPanel.getSelectionModel().getSelections();
				var record = gridPanel.getSelectionModel().getSelected();
				
				Ext.Ajax.request({params: {id: record.data.id},
					url: "delete",
					success: function(response) {
						var resp = Ext.util.JSON.decode(response.responseText);
						if (resp.success) {
							Ext.MessageBox.alert('提示','数据删除成功');
						} else {
							Ext.MessageBox.alert('操作失败', resp.msg);
						}
						gridPanel.getStore().load();
					},
					failure:ajaxRequestFailure
				});
			}
		}
	
	
});

