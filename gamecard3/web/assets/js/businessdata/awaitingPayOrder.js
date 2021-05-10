Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list?orderStatus=1",
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
		        {name:"payTm"}
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	
	var queryForm = new Ext.form.FormPanel({
		frame:true,
		region:"north",
		autoHeight:true,
		items: [

			new Ext.form.FieldSet({
				labelWidth:80,
				autoHeight:true,
				layout:"column",
				title:"查询条件",
				items:[{
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
						},{
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
						},{
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
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:150,
									name:"memberName",
									fieldLabel:"会员"
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
			                              {header:"订单总金额",dataIndex:"orderTotalPrice"},
			                              {header:"折扣后订单总金额",dataIndex:"discountTotalPrice"},
			                              {header:"优惠金额",dataIndex:"discountPrice"},
			                              {header:"订单生成时间",width:150,renderer:datetimeRenderer,dataIndex:"createTm"},
			                              {header:"物流运费",dataIndex:"shippingOptionAmount"},
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
		}
		return value ;
	}
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnOrderDetail = new Ext.Button({text:"查看订单详情",iconCls:'detail',handler:onOrderDetail }) ;
	var btnDiscount = new Ext.Button({text:"打折",iconCls:'revert',handler:onDiscount }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnOrderDetail,"-",btnDiscount],
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
	var operator_type ;
	function onSearch(){
		
		if (queryForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return ;
		}
		gridPanel.getStore().baseParams = queryForm.getForm().getValues();
		gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
		gridPanel.getStore().load();
	}
	
	
	/**********************查看订单详情*************************/
		var orderDetailGridStore = new Ext.data.JsonStore({
			//autoLoad:true ,
			url : '../orderDetail/listOrderDetailsByOrderId',
			fields : [ {name : 'id'}, 
			           {name : 'orderId'}, 
			           {name : 'orderNo'}, 
			           {name : 'productId'}, 
			           {name : 'productNo'}, 
			           {name : 'productName'}, 
			           {name : 'amount'}, 
			           {name : 'size'}, 
			           {name : 'color'}, 
			           {name : 'unitPrice'}, 
			           {name : 'basePrice'}
			],
			root : 'root'
		});
		var orderDetailcheckboxSelectionMode = new Ext.grid.CheckboxSelectionModel({});
		var orderDetailGridPanel = new Ext.grid.GridPanel({
			frame:true,
			loadMask:true,
			height:300,
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
		var orderDetailWin = new Ext.Window({
			autoHeight:true,
			constrainHeader : true,
			width : 1050,
			closeAction : "hide",
			resizable : false,
			isEditing : false,
			plain : true,
			modal : true,
			items :  [orderDetailGridPanel]
		});
		
		function onOrderDetail() {
			var records = gridPanel.getSelectionModel().getSelections();
			var record = gridPanel.getSelectionModel().getSelected();
			if (records.length == 1) {
				orderDetailWin.setTitle("订单详情");
				orderDetailWin.show();
				orderDetailGridStore.baseParams["orderId"] = record.data.id;
				orderDetailGridStore.load() ;
				
			} else if (records.length < 1) {
				Ext.Msg.alert("提示","请选择一笔数据行");
			} else if (records.length > 1) {
				Ext.Msg.alert("提示","不能同时操作多条数据");
			}

		}
	
	/*************************************************************/
	
	// ajax请求失败处理事件
	function ajaxRequestFailure(response) {
		  if (response.status == 0) {
		      Ext.Msg.alert("提示", "无法连接到服务器，请检查网络是否正常");
		  } else if (response.status == -1) {
		      Ext.Msg.alert("提示", "服务器处理超时，请稍后再试");
		  } else {
				Ext.Msg.alert("提示","系统出现异常,请与管理员联系");
			}
		}
	
	function showFormSubmitFailureInfo(form, action) {
		var msg = '';
		if (action && action.result && action.result.error) {
			Ext.Msg.alert("提示","系统出现了异常,请与管理员联系!");
		} else {
			if (action.failureType === Ext.form.Action.CONNECT_FAILURE) {
				if (action.response.status == 0) {
					msg = "连接失败,无法连接到服务器!";
				} else if (action.response.status == -1) {
					msg = "服务器处理超时!";
				} else {
					msg = String.format("错误代码:{0}, 错误描述:{1}!",action.response.status,action.response.statusText);
				}
			} else if (action.failureType === Ext.form.Action.CLIENT_INVALID) {
				msg = "请将表单填写正确!";
			}
			Ext.Msg.alert("提示", msg);
		}
	}
	/**********************打折***************************/
	var discountForm = new Ext.form.FormPanel({
		region : "center",
		frame : true,
		fileUpload : false,
		method : "POST",
		items : [{
			layout : "column",
			items : [{
						columnWidth : 0.5,
						layout : 'form',
						labelWidth : 100,
						items : [{
									xtype : 'hidden',
									name:"id"
								},{
									xtype : 'numberfield',
									allowBlank:false,
									name : "orderTotalPrice",
									decimalPrecision :2,
									readOnly:true,
									value:0,
									minValue:0,
									fieldLabel : "订单总金额"
								}]
					},{
						columnWidth : 0.5,
						layout : 'form',
						labelWidth : 100,
						items : [{
									xtype : 'numberfield',
									allowBlank:false,
									name : "discountOrderTotalPrice",
									decimalPrecision :2,
									readOnly:true,
									value:0,
									minValue:0,
									fieldLabel : "折后订单总金额"
								}]
					},{
						columnWidth : 0.5,
						layout : 'form',
						labelWidth : 100,
						items : [{
									xtype : 'numberfield',
									allowBlank:false,
									name : "discountPrice",
									decimalPrecision :2,
									readOnly:true,
									value:0,
									minValue:0,
									fieldLabel : "折扣金额"
								}]
					}
			]
		}]
	});
	var discountGridStore = new Ext.data.JsonStore({
		//autoLoad:true ,
		url : '../orderDetail/listOrderDetailsByOrderId',
		fields : [ {name : 'id'}, 
		           {name : 'orderId'}, 
		           {name : 'orderNo'}, 
		           {name : 'productId'}, 
		           {name : 'productName'}, 
		           {name : 'amount'}, 
		           {name : 'unitPrice'}, 
		           {name : 'basePrice'}
		],
		root : 'root'
	});
	var discountcheckboxSelectionMode = new Ext.grid.CheckboxSelectionModel({});
	var his_rowIndex ;
	var discountGridPanel = new Ext.grid.EditorGridPanel({
		id:"discountGridPanelId",
		frame:true,
		loadMask:true,
		forceFit: true,
		height:200,
		store : discountGridStore,
		sm : discountcheckboxSelectionMode,
		enableHdMenu : false,
		cm : new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
				{header : '订单号',dataIndex : "orderNo",	sortable : false},
				{header : '产品名称',dataIndex : "productName",	sortable : false},
				{header : '折后订单单价',dataIndex : "unitPrice",sortable : false,editor:new Ext.form.NumberField({
					enableKeyEvents:true,listeners:{
					keyup:function(o){
						var grid = Ext.getCmp("discountGridPanelId");
						var selRecord = grid.getSelectionModel().getSelected();
						var store = grid.getStore() ;
						var size = store.getTotalCount() ;
						var discountPrice = 0 ;//折扣金额
						for(var i =0 ;i<size;i++){
							var record = store.getAt(i) ;
							if(selRecord.get('id') == record.get('id')){
								record.set('unitPrice',o.getValue());
							}
							discountPrice = discountPrice+(record.data.basePrice - record.data.unitPrice)*record.data.amount;
						}
						discountForm.getForm().findField("discountPrice").setValue(discountPrice);
						
						var orderTotalPrice = discountForm.getForm().findField("orderTotalPrice").getValue() ;
						discountForm.getForm().findField("discountOrderTotalPrice").setValue(orderTotalPrice - discountPrice);
						
					}//end keyup
				} })},
				{header : '原订单单价',dataIndex : "basePrice",	sortable : false},
				{header : '件数',dataIndex : "amount",	sortable : false}
				])
	});
	
	var btnDiscountSave = new Ext.Button({text : "保存",iconCls:'save',handler:onDiscountSave}) ;
	var discountWin = new Ext.Window({
		autoHeight:true,
		constrainHeader : true,
		width : 700,
		closeAction : "hide",
		resizable : false,
		isEditing : false,
		plain : true,
		modal : true,
		listeners:{
			 hide:function(){
				 discountForm.getForm().reset();
			 }
			},
		tbar : [btnDiscountSave],
		items :  [discountForm,discountGridPanel]
	});
	
	// 打折
	function onDiscount() {
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			discountWin.setTitle("打折");
			discountWin.show();
			discountForm.getForm().reset() ;
			discountForm.getForm().findField("id").setValue(record.data.id);
			
			discountForm.getForm().findField("orderTotalPrice").setValue(record.data.orderTotalPrice);
			discountForm.getForm().findField("discountPrice").setValue(record.data.discountPrice);
			discountForm.getForm().findField("discountOrderTotalPrice").setValue(record.data.discountTotalPrice);
			discountGridStore.baseParams["orderId"] = record.data.id;
			discountGridStore.load() ;
			
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}
	}
	//打折保存
	function onDiscountSave(){
		if(discountForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		
		var orderDetailList = "";
		var store = discountGridPanel.getStore() ;
		var size = store.getTotalCount() ;
		if(size > 0){
			orderDetailList = "[";
		}
		for(var i =0 ;i<size;i++){
			var record = store.getAt(i) ;
			if(i == (size -1)){
				orderDetailList = orderDetailList +"{id:"+record.data.id+",unitPrice:"+record.data.unitPrice+",basePrice:"+record.data.basePrice+"}"
			}else{
				orderDetailList = orderDetailList +"{id:"+record.data.id+",unitPrice:"+record.data.unitPrice+",basePrice:"+record.data.basePrice+"},"
			}
		}
		if(size > 0){
			orderDetailList = orderDetailList +"]";
		}
        //提交
        discountForm.getForm().submit(
				{
					params:{orderDetailList:orderDetailList},
					url:"discount",
					success : function(form,action){
						if (action.result.msg) {
							Ext.Msg.alert("提示",action.result.msg);
						} else {
							Ext.Msg.alert("提示","保存成功");
							discountWin.hide();
							gridStore.reload();
						}
					},
					failure : showFormSubmitFailureInfo,
					waitMsg : "正在执行操作...",
					waitTitle : "请稍后..."
				});
		
	}
	
});

