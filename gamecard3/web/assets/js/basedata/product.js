	//图片下载
	function downloadImg(id,downloadType){
		window.location="downloadImg?id="+id+"&downloadType="+downloadType ;
		
	}
	
	//图片删除
	function deleteImg(id,type){
		
		Ext.Ajax.request({params: {id: id,type:type},
			url: "deleteImg",
			success: function(response) {
				var resp = Ext.util.JSON.decode(response.responseText);
				if (resp.success) {
					Ext.MessageBox.alert('提示','图片删除成功');
					if(type == 0){
						Ext.getCmp("mainImg_dis_id").setValue("");
						Ext.getCmp("mainImgDesc").setValue("");
					}else if(type == -1){
						Ext.getCmp("hotImg").setValue("");
						Ext.getCmp("hotDesc").setValue("");
					}else if(type == 101){
						Ext.getCmp("d_zoomImg1").setValue("");
					}else if(type == 102){
						Ext.getCmp("d_zoomImg2").setValue("");
					}else if(type == 103){
						Ext.getCmp("d_zoomImg3").setValue("");
					}else if(type == 104){
						Ext.getCmp("d_zoomImg4").setValue("");
					}else if(type == 105){
						Ext.getCmp("d_zoomImg5").setValue("");
					}else if(type == 106){
						Ext.getCmp("d_zoomImg6").setValue("");
					}else if(type == 107){
						Ext.getCmp("d_zoomImg7").setValue("");
					}else if(type == 108){
						Ext.getCmp("d_zoomImg8").setValue("");
					}else if(type == 109){
						Ext.getCmp("d_zoomImg9").setValue("");
					}else if(type == 110){
						Ext.getCmp("d_zoomImg10").setValue("");
					}
					Ext.getCmp("gridPanelId").getStore().reload();
					
					
				} else {
					Ext.MessageBox.alert('操作失败', resp.msg);
				}
			}
		});
		
	}
	
	
Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"productNo"},
		        {name:"categoryId"},
		        {name:"categoryName"},
		        {name:"shortName"},
		        {name:"productName"},
		        {name:"mainImgPath"},
		        {name:"mainImgDesc"},
		        {name:"basePrice"},
		        {name:"preSellPrice"},
		        {name:"actualSellPrice"},
		        {name:"weight"},
		        {name:"available"},
		        {name:"sales"},
		        {name:"baseSales"},
		        {name:"provider"},
		        {name:"purchaseTm"},
		        {name:"brand"},
		        {name:"discount"},
		        {name:"isAdvice"},
		        {name:"isHot"},
		        {name:"isCustomerLike"},
		        {name:"isNew"},
		        {name:"isDiscount"},
		        {name:"downFlag"},
		        {name:"star"},
		        {name:"hotImgPath"},
		        {name:"hotDesc"},
		        {name:"desc"},
		        {name:"zoomImg1"},
		        {name:"zoomImg2"},
		        {name:"zoomImg3"},
		        {name:"zoomImg4"},
		        {name:"zoomImg5"},
		        {name:"zoomImg6"},
		        {name:"zoomImg7"},
		        {name:"zoomImg8"},
		        {name:"zoomImg9"},
		        {name:"zoomImg10"},
		        {name:"sortNo"}
		        
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	
	var queryForm_atrr_1 = new Ext.form.TextField({width:150,name:"shortName",fieldLabel:"产品简称"});
	var queryForm_atrr_2 = new Ext.form.TextField({width:150,name:"productName",fieldLabel:"产品全称"});
	
	var queryForm_atrr_3 = new Ext.form.ComboBox({width:150,hiddenName:"categoryId",typeAhead: true,mode: 'remote',editable: false,displayField:"text",valueField:"key",triggerAction: "all",fieldLabel:"分类",emptyText:"全部",selectOnFocus:true,store:new Ext.data.JsonStore({
		autoLoad:true,
		url:'../category/listBy',
		fields:[{name: 'key', mapping:'id'},
		        {name: 'text', mapping:'name'}],
		root:'root',
		listeners:{
			beforeload:function(s){
				//s.baseParams["tryeData.tryeBreed"] = Ext.getCmp('tryeBreed').getValue();
		  	},
		  	load:function(t){
				  var all = new Ext.data.Record([]);
				  all.set('key', '');
                  all.set('text', '全部');
                  t.insert(0, [all]);
			}
      }
	}),
	listeners:{
		'beforequery': function(qe){
			//每次都查询
			delete qe.combo.lastQuery;  
		}
	}
	});
	
	var queryForm_atrr_4 = new Ext.form.TextField({width:150,name:"provider",fieldLabel:"供货商"});
	var queryForm_atrr_5 = new Ext.form.ComboBox({hiddenName:"isAdvice",store:new Ext.data.SimpleStore({data: [['','全部'],['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",emptyText:'全部',width:150,triggerAction:"all",fieldLabel:"是否首页推介",editable:false,mode:"local"});
	var queryForm_atrr_6 = new Ext.form.ComboBox({hiddenName:"isHot",store:new Ext.data.SimpleStore({data: [['','全部'],['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",emptyText:'全部',width:150,triggerAction:"all",fieldLabel:"是否热门商品",editable:false,mode:"local"});
	var queryForm_atrr_7 = new Ext.form.ComboBox({hiddenName:"isCustomerLike",store:new Ext.data.SimpleStore({data: [['','全部'],['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",emptyText:'全部',width:150,triggerAction:"all",fieldLabel:"是否客户喜欢",editable:false,mode:"local"});
	var queryForm_atrr_8 = new Ext.form.ComboBox({hiddenName:"isNew",store:new Ext.data.SimpleStore({data: [['','全部'],['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",emptyText:'全部',width:150,triggerAction:"all",fieldLabel:"是否最新产品",editable:false,mode:"local"});
	var queryForm_atrr_9 = new Ext.form.ComboBox({hiddenName:"downFlag",store:new Ext.data.SimpleStore({data: [['','全部'],['1','下架'],['0','在线']], fields: ['key','value']}),valueField:"key",displayField:"value",emptyText:'全部',width:150,triggerAction:"all",fieldLabel:"在线状态",editable:false,mode:"local"});
	var queryForm_atrr_10 = new Ext.form.TextField({width:150,name:"productNo",fieldLabel:"产品编号"});
	var queryForm_atrr_11 = new Ext.form.ComboBox({hiddenName:"isDiscount",store:new Ext.data.SimpleStore({data: [['','全部'],['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",emptyText:'全部',width:150,triggerAction:"all",fieldLabel:"是否特价商品",editable:false,mode:"local"});
	
	var queryForm = new Ext.form.FormPanel({
		frame:true,
		region:"north",
		autoHeight:true,
		items: [

			new Ext.form.FieldSet({
				//labelWidth:80,
				autoHeight:true,
				layout:"column",
				title:"查询条件",
				items:[

						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_1]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_2]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_3]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_4]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_5]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_6]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_7]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_8]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_9]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_10]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_11]
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
			id:"gridPanelId",
			region:"center",
			loadMask:true,
			tbar:pagingBar,
			store:gridStore,
			sm: checkboxSelectionMode,
			//stripeRows : true,
			cm: new Ext.grid.ColumnModel([
			                              checkboxSelectionMode,
			                              {header:"id",dataIndex:"id"},
			                              {header:"产品编号",width:125,dataIndex:"productNo"},
			                              {header:"排序",width:60,dataIndex:"sortNo"},
			                              {header:"分类",width:150,dataIndex:"categoryName"},
			                              {header:"产品简称",width:200,dataIndex:"shortName"},
			                              {header:"产品全名称",width:200,dataIndex:"productName"},
			                              {header:"在线状态",renderer:downFlagRenderer,dataIndex:"downFlag"},
			                              {header:"主图片",dataIndex:"mainImgPath"},
			                              {header:"主图片描述",dataIndex:"mainImgDesc"},
			                              {header:"进价（RMB）",dataIndex:"basePrice"},
			                              {header:"预设销售价格（USD）",dataIndex:"preSellPrice"},
			                              {header:"实际销售价格（USD）",dataIndex:"actualSellPrice"},
			                              {header:"重量",dataIndex:"weight"},
			                              {header:"库存量",dataIndex:"available"},
			                              {header:"销量",dataIndex:"sales"},
			                              {header:"系统初始化销量",dataIndex:"baseSales"},
			                              {header:"供货商",dataIndex:"provider"},
			                              {header:"进货时间",width:150,renderer:datetimeRenderer,dataIndex:"purchaseTm"},
			                              {header:"折扣",dataIndex:"discount"},
			                              {header:"是否首页推介",renderer:YorNRenderer,dataIndex:"isAdvice"},
			                              {header:"是否热门商品",renderer:YorNRenderer,dataIndex:"isHot"},
			                              {header:"是否客户喜欢",renderer:YorNRenderer,dataIndex:"isCustomerLike"},
			                              {header:"是否最新产品",renderer:YorNRenderer,dataIndex:"isNew"},
			                              {header:"是否特价商品",renderer:YorNRenderer,dataIndex:"isDiscount"},
			                              {header:"星数等级",renderer:starRenderer,dataIndex:"star"},
			                              {header:"首页推介图",dataIndex:"hotImgPath"}
			                              ])
	});
	
	function downFlagRenderer(v){
		var value = "" ;
		if(v == '1'){
			value = "<font color='red'>已下架</font>" ;
		}else if(v == '0'){
			value = "在线" ;
		}
		return value;
	}
	function datetimeRenderer(v){
		var value = v ;
		if(v != null && v != ''){
			value = formatDate(new Date(v.time),"y-m-d") ;
		}
		return value ;
	}
	function YorNRenderer(v){
		var value = "" ;
		if(v == '1'){
			value = "<font color='red'>是</font>" ;
		}else if(v == '0'){
			value = "否" ;
		}
		return value ;
	}
	
	function starRenderer(v){
		var value = "" ;
		if(v == '1'){
			value = "一级" ;
		}else if(v == '2'){
			value = "二级" ;
		}else if(v == '3'){
			value = "三级" ;
		}else if(v == '4'){
			value = "四级" ;
		}else if(v == '5'){
			value = "五级" ;
		}
		return value ;
	}
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnAdd = new Ext.Button({text:"新增",iconCls:'add',handler:onAdd }) ;
	var btnEdit = new Ext.Button({text:"修改",iconCls:'edit',handler:onUpdate }) ;
	var btnEditDesc = new Ext.Button({text:"编辑产品详情",iconCls:'edit',handler:onEditDesc }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnUpload = new Ext.Button({text:"上传图片",iconCls:'revert',handler:onUpload }) ;
	var btnUploadZoom = new Ext.Button({text:"上传缩略图",iconCls:'revert',handler:onUploadZoom }) ;
	var btnSort = new Ext.Button({text:"设置排序",iconCls:'goto',handler:onSort }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnAdd,"-",btnEdit,"-",btnEditDesc,"-",btnDelete,'-',btnUpload,'-',btnUploadZoom,"-",btnSort],
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

	var editForm_atrr_0 = new Ext.form.Hidden({name:"id"});
	
	var editForm_atrr_00 = new Ext.form.Hidden({name:"categoryId"});
	
	var editForm_atrr_1 = new Ext.form.TriggerField({
		triggerAction:'all',
		allowBlank:false,
		width:150, 
		name:"categoryName",
		editable:false,
		fieldLabel:'选择类别',
		triggerClass : 'x-form-search-trigger',
		onTriggerClick :function(obj){
	    	selectCategoryClick(obj) ;
	    }
	});
	function selectCategoryClick(obj){
		selectCategoryWin.setTitle("选择所属类别");
		selectCategoryWin.show() ;
	}
	//选择所属类别
	function onSelectCategory(){
	}
	var selectCategoryNode ;
	//选择所属类别窗口

	var treeLoader = new Ext.tree.TreeLoader({
		autoLoad:true,
		url : "../category/findCategoryTree",
		//baseAttrs:{uiProvider :Ext.tree.TreeCheckNodeUI},
		listeners:{
		    beforeload:function(treeLoader, node){
		    // this.baseParams.pid = node.attributes.id;
		    }
		}
	});
	var rootNode = new Ext.tree.AsyncTreeNode({
		id : "0",
		text:"分类树",
	});
	var selectCategoryWin = new Ext.Window({
		width:600,
		height:400,
		frame:true,
		closeAction:'hide',
		layout:'fit',
		buttons: [
		          	{text:"确定",iconCls:'revert',handler:function(){
		          		if(selectCategoryNode == null){
	          				Ext.MessageBox.alert("提示","请选择所属类别");
	          				return ;
	          			}
	          			editForm.getForm().findField("categoryId").setValue(selectCategoryNode.id);
	          			editForm.getForm().findField("categoryName").setValue(selectCategoryNode.text);
	          			
	          			selectCategoryWin.hide() ;
						
		          	}},
					{text:"取消",iconCls:'close',handler:function() {selectCategoryWin.hide();}
		}],
		items:[{
    	  	xtype:"treepanel",
    	  	id:"categoryTree",
    	  	region:"center",
			autoScroll : true,
			split : true,
			width : 250,
			//rootVisible: false,
			//checkModel:"cascade",
			//onlyLeafCheckable:false,
			loader: treeLoader,
	        root: rootNode,
	        listeners:{
			    click : function(node) {
			    	selectCategoryNode = node ;
			    	selectCategoryWin.setTitle("选择所属类别"+"<font color='red'> ->"+node.text+"</font>");
			    }
			}
    	  	
		}]
	});
	
	var editForm_atrr_2 = new Ext.form.TextField({width:150,name:"shortName",maxLength:200,allowBlank:false,fieldLabel:"产品简称"});
	
	var editForm_atrr_3 = new Ext.form.TextField({width:150,name:"productName",maxLength:200,allowBlank:false,fieldLabel:"产品全名称"});

	var editForm_atrr_4 = new Ext.form.NumberField({width:150,name:"basePrice",allowBlank:false,fieldLabel:"进价（RMB）",decimalPrecision:2,
		listeners:{
			blur:function(obj){
				var v = obj.value*1.3/5.9 ;
				editForm.getForm().findField("actualSellPrice").setValue(v) ;
				
				editForm.getForm().findField("preSellPrice").setValue(v*1.5/1.3) ;
			}
		}
		});
	
	var editForm_atrr_5 = new Ext.form.NumberField({width:150,name:"preSellPrice",allowBlank:false,fieldLabel:"预设销售价格（USD）",decimalPrecision:2});
	
	var editForm_atrr_6 = new Ext.form.NumberField({width:150,name:"actualSellPrice",allowBlank:false,fieldLabel:"实际销售价格（USD）",decimalPrecision:2});
	
	var editForm_atrr_7 = new Ext.form.NumberField({width:150,name:"weight",allowBlank:false,fieldLabel:"重量(KGS)",decimalPrecision:2});
	
	var editForm_atrr_8 = new Ext.form.NumberField({width:150,name:"available",allowBlank:false,fieldLabel:"库存量",value:999,decimalPrecision:0});
	
	var editForm_atrr_9 = new Ext.form.NumberField({width:150,name:"sales",allowBlank:false,fieldLabel:"销量",value:0,decimalPrecision:0});
	
	var editForm_atrr_10 = new Ext.form.NumberField({width:150,name:"baseSales",allowBlank:false,fieldLabel:"系统初始化销量",value:985,decimalPrecision:0});
	
	var editForm_atrr_11 = new Ext.form.TextField({width:150,name:"provider",maxLength:60,allowBlank:false,fieldLabel:"供货商"});

	var editForm_atrr_12 = new Ext.form.DateField({width:150,name:"purchaseTm",allowBlank:false,format:"Y-m-d",value:new Date(),fieldLabel:"进货时间"});

	var editForm_atrr_13 = new Ext.form.NumberField({width:150,name:"discount",allowBlank:false,fieldLabel:"折扣",value:1,decimalPrecision:2});
	
	var editForm_atrr_14 = new Ext.form.ComboBox({hiddenName:"isAdvice",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",value:'0',width:150,triggerAction:"all",fieldLabel:"是否首页推介",editable:false,mode:"local"});

	var editForm_atrr_15 = new Ext.form.ComboBox({hiddenName:"isHot",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",value:'0',width:150,triggerAction:"all",fieldLabel:"是否热门商品",editable:false,mode:"local"});

	var editForm_atrr_16 = new Ext.form.ComboBox({hiddenName:"isCustomerLike",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",value:'0',width:150,triggerAction:"all",fieldLabel:"是否客户喜欢",editable:false,mode:"local"});
	
	var editForm_atrr_17 = new Ext.form.ComboBox({hiddenName:"isNew",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",value:'1',width:150,triggerAction:"all",fieldLabel:"是否最新产品",editable:false,mode:"local"});

	var editForm_atrr_18 = new Ext.form.ComboBox({hiddenName:"star",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','一级'],['2','二级'],['3','三级'],['4','四级'],['5','五级']], fields: ['key','value']}),valueField:"key",displayField:"value",value:'5',width:150,triggerAction:"all",fieldLabel:"星数等级",editable:false,mode:"local"});

	var editForm_atrr_19 = new Ext.form.ComboBox({width:150,hiddenName:"brand",typeAhead: true,mode: 'remote',editable: false,displayField:"text",valueField:"key",triggerAction: "all",fieldLabel:"品牌",
		selectOnFocus:true,store:new Ext.data.JsonStore({
			autoLoad:true,
			url:'../brand/listBy',
			fields:[{name: 'key', mapping:'brand'},
			        {name: 'text', mapping:'brand'}],
			root:'root',
			listeners:{
				beforeload:function(s){
					//s.baseParams["tryeData.tryeBreed"] = Ext.getCmp('tryeBreed').getValue();
			  	},
			  	load:function(t){
					 /* var all = new Ext.data.Record([]);
					  all.set('key', '');
	                  all.set('text', '全部');
	                  t.insert(0, [all]);*/
				}
	      }
		}),
		listeners:{
			/*'beforequery': function(qe){
				//每次都查询
				delete qe.combo.lastQuery;  
			}*/
		}
	});
	
	
	var editForm_atrr_20 = new Ext.form.ComboBox({hiddenName:"downFlag",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','下架'],['0','在线']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"在线状态",value:0,editable:false,mode:"local"});
	var editForm_atrr_21 = new Ext.form.ComboBox({hiddenName:"isDiscount",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"是否特价商品",value:0,editable:false,mode:"local"});
	
	/*******************颜色***********************/
	var Color = Ext.data.Record.create([{
        name: 'key',
        type: 'string'
    },{
        name: 'price',
        type: 'float'
    }]);
	var colorEditor = new Ext.ux.grid.RowEditor({
        saveText: 'Update'
    });
	var colorGridStore = new Ext.data.JsonStore({
		url : '../keyCategory/listBy?type=1',
		fields : [ {name : 'id'}, 
		           {name : 'productId'}, 
		           {name : 'key'}, 
		           {name : 'type'}, 
		           {name : 'price'}
		],
		root : 'root'
	});
	var colorGridPanel = new Ext.grid.GridPanel({
		loadMask:true,
		autoScroll:true,
		height:200,
		store : colorGridStore,
		enableHdMenu : false,
		tbar: [{
            iconCls: 'add',
            text: 'Add',
            handler: function(){
                var e = new Color({
                    key: '',
                    price: '1',
                    active: true
                });
                colorEditor.stopEditing();
                colorGridPanel.getStore().insert(0, e);
                colorGridPanel.getView().refresh();
                colorGridPanel.getSelectionModel().selectRow(0);
                colorEditor.startEditing(0);
            }
        },{
            iconCls: 'delete',
            text: 'Remove',
            handler: function(){
            	colorEditor.stopEditing();
                var s = colorGridPanel.getSelectionModel().getSelections();
                for(var i = 0, r; r = s[i]; i++){
                	colorGridPanel.getStore().remove(r);
                }
            }
        }],
	    plugins: [colorEditor],
		columns: [
					{
					    header: '颜色',
					    dataIndex: 'key',
					    width: 220,
					    sortable: true,
					    editor: {
					        xtype: 'textfield',
					        allowBlank: false
					    }
					},{
			            xtype: 'numbercolumn',
			            header: '价格',
			            dataIndex: 'price',
			            width: 200,
			            sortable: true,
			            editor: {
			                xtype: 'numberfield',
			                allowBlank: false,
			                minValue: 0.01,
			                decimalPrecision:2
			            }
			        }
		          ]
	});
	/*******************尺寸***********************/
	var Size = Ext.data.Record.create([{
        name: 'key',
        type: 'string'
    },{
        name: 'price',
        type: 'float'
    }]);
	var sizeEditor = new Ext.ux.grid.RowEditor({
        saveText: 'Update'
    });
	var sizeGridStore = new Ext.data.JsonStore({
		url : '../keyCategory/listBy?type=2',
		fields : [ {name : 'id'}, 
		           {name : 'productId'}, 
		           {name : 'key'}, 
		           {name : 'type'}, 
		           {name : 'price'}
		],
		root : 'root'
	});
	var sizeGridPanel = new Ext.grid.GridPanel({
		loadMask:true,
		autoScroll:true,
		height:200,
		store : sizeGridStore,
		enableHdMenu : false,
		tbar: [{
            iconCls: 'add',
            text: 'Add',
            handler: function(){
                var e = new Size({
                    key: '',
                    price: '1',
                    active: true
                });
                sizeEditor.stopEditing();
                sizeGridPanel.getStore().insert(0, e);
                sizeGridPanel.getView().refresh();
                sizeGridPanel.getSelectionModel().selectRow(0);
                sizeEditor.startEditing(0);
            }
        },{
            iconCls: 'delete',
            text: 'Remove',
            handler: function(){
            	sizeEditor.stopEditing();
                var s = sizeGridPanel.getSelectionModel().getSelections();
                for(var i = 0, r; r = s[i]; i++){
                	sizeGridPanel.getStore().remove(r);
                }
            }
        }],
	    plugins: [sizeEditor],
		columns: [
					{
					    header: '尺寸大小',
					    dataIndex: 'key',
					    width: 220,
					    sortable: true,
					    editor: {
					        xtype: 'textfield',
					        allowBlank: false
					    }
					},{
			            xtype: 'numbercolumn',
			            header: '价格',
			            dataIndex: 'price',
			            width: 200,
			            sortable: true,
			            editor: {
			                xtype: 'numberfield',
			                allowBlank: false,
			                minValue: 0.01,
			                decimalPrecision:2
			            }
			        }
		          ]
	});
	/*******************特性值***********************/
	var KV = Ext.data.Record.create([{
        name: 'key',
        type: 'string'
    },{
        name: 'value',
        type: 'string'
    },{
        name: 'sort',
        type: 'float'
    }]);
	var kvEditor = new Ext.ux.grid.RowEditor({
        saveText: 'Update'
    });
	var kvGridStore = new Ext.data.JsonStore({
		url : '../keyValue/listBy',
		fields : [ {name : 'id'}, 
		           {name : 'productId'}, 
		           {name : 'key'}, 
		           {name : 'value'},
		           {name : 'sort'}
		],
		root : 'root'
	});
	var kvGridPanel = new Ext.grid.GridPanel({
		loadMask:true,
		autoScroll:true,
		height:200,
		store : kvGridStore,
		enableHdMenu : false,
		tbar: [{
            iconCls: 'add',
            text: 'Add',
            handler: function(){
                var e = new KV({
                    key: '',
                    value: '',
                    sort:'1',
                    active: true
                });
                kvEditor.stopEditing();
                kvGridPanel.getStore().insert(0, e);
                kvGridPanel.getView().refresh();
                kvGridPanel.getSelectionModel().selectRow(0);
                kvEditor.startEditing(0);
            }
        },{
            iconCls: 'delete',
            text: 'Remove',
            handler: function(){
            	kvEditor.stopEditing();
                var s = kvGridPanel.getSelectionModel().getSelections();
                for(var i = 0, r; r = s[i]; i++){
                	kvGridPanel.getStore().remove(r);
                }
            }
        }],
	    plugins: [kvEditor],
		columns: [
					{
					    header: '特性值名称',
					    dataIndex: 'key',
					    width: 220,
					    sortable: true,
					    editor: {
					        xtype: 'textfield',
					        allowBlank: false
					    }
					},{
			            header: '特性值',
			            dataIndex: 'value',
			            width: 200,
			            sortable: true,
			            editor: {
			                xtype: 'textfield',
			                allowBlank: false
			            }
			        },{
			            header: '排序',
			            dataIndex: 'sort',
			            width: 200,
			            sortable: true,
			            editor: {
			                xtype: 'numberfield',
			                allowBlank: false,
			                minValue: 0,
			                decimalPrecision:0
			            }
			        }
		          ]
	});
	/*******************************************/
	var tabPanel = new Ext.TabPanel({
		autoWidth:true,
		height:240,
        activeTab: 0,
		items: [ 
			new Ext.Panel({title:"颜色",layout:"form"
				,items: [colorGridPanel]
			})
			,new Ext.Panel({title:"产品特性值",layout:"form"
				,items: [kvGridPanel]
			})
			,new Ext.Panel({title:"尺寸",layout:"form"
				,items: [sizeGridPanel]
			})
		]
	});
	var editForm = new Ext.form.FormPanel({region:"center",height:450,autoScroll:true,frame:true,fileUpload:false,width:900,timeout:300
		,items: [
			new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_1,editForm_atrr_0,editForm_atrr_00]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_2]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_3]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_4]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_5]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_6]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_7]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_8]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_9]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_10]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_11]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_12]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_13]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_14]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_15]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_16]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_17]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_18]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_19]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_20]})
					,new Ext.Panel({columnWidth:.33,layout:"form",items: [editForm_atrr_21]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [ new Ext.Panel({columnWidth:1,layout:"form",items: [tabPanel]})
				]
			})
		]
	});
	
	
	var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSaveOrUpdate}) ;
	// 编辑窗口
	var editWin = new Ext.Window({height:550,constrainHeader:true,width:1020,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,tbar: [
		        btnSave
		]
		,items: [
		         editForm
		]
	});
	
	/****************begin upload**************************/
	var uploadForm_atrr_1 = new Ext.form.TextField({name:"mainImg",inputType : "file",fieldLabel:"主图片"});
	var uploadForm_atrr_1_0 = new Ext.form.Label({html:"&nbsp;&nbsp;尺寸(<font color='red'>200X200</font>)"});
	var uploadForm_atrr_1_1 = new Ext.form.DisplayField({id:"mainImg_dis_id" ,value:""});
	var uploadForm_atrr_1_2 = new Ext.form.HtmlEditor({id:"mainImgDesc",height:200,width:600,name:"mainImgDesc",allowBlank:true,fieldLabel:"主图片描述"});
	
	
	var uploadForm_atrr_2 = new Ext.form.TextField({name:"hotImg",inputType : "file",fieldLabel:"首页推介图"});
	var uploadForm_atrr_2_0 = new Ext.form.Label({html:"&nbsp;&nbsp;尺寸(<font color='red'>1560X500</font>)"});
	var uploadForm_atrr_2_1 = new Ext.form.DisplayField({id:"hotImg" ,value:""});
	var uploadForm_atrr_2_2 = new Ext.form.HtmlEditor({id:"hotDesc",height:200,width:600,name:"hotDesc",allowBlank:true,fieldLabel:"首页推介描述"});
	
	
	var uploadForm_atrr_3 = new Ext.form.Hidden({name:"id"});
	
	var uploadForm_atrr_4 = new Ext.form.Hidden({name:"categoryId"});
	
	var uploadForm = new Ext.form.FormPanel({region:"center",frame:true,fileUpload:true,autoWidth:true,timeout:300,method:"POST"
		,items: [
			new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({width:325,layout:"form",items: [uploadForm_atrr_1]})
					,new Ext.Panel({width:100,layout:"form",items: [uploadForm_atrr_1_0]})
					,new Ext.Panel({width:300,layout:"form",items: [uploadForm_atrr_1_1]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({height:200,width:720,layout:"form",items: [uploadForm_atrr_1_2]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({width:325,layout:"form",items: [uploadForm_atrr_2]})
					,new Ext.Panel({width:100,layout:"form",items: [uploadForm_atrr_2_0]})
					,new Ext.Panel({width:300,layout:"form",items: [uploadForm_atrr_2_1]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
						new Ext.Panel({height:200,width:720,layout:"form",items: [uploadForm_atrr_2_2,uploadForm_atrr_3,uploadForm_atrr_4]})
					]
				})
		]
	});
	var btnUploadSave = new Ext.Button({text:"保存",iconCls:'save',handler:onUploadSave}) ;
	// 编辑窗口
	var uploadWin = new Ext.Window({height:500,width:800,layout:"border",closeAction:"hide",isEditing:false,plain:true,modal:true
		,tbar: [
		        btnUploadSave
		]
		,items: [{
				 xtype:'panel',
		         autoScroll:true,
		         region:'center',
		         height:300,
		         items:[
		        	 uploadForm
		         ]
		}]
	});
	function onUpload(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			uploadWin.setTitle("上传");
			uploadWin.show();
			uploadForm.getForm().reset();		
			var obj = {};
			for (p in record.data) {
				if(p == 'mainImgPath' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",0);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",0);' />" ;
					uploadForm_atrr_1_1.setValue(html+html2) ;
					continue ;
				}else if(p == 'hotImgPath' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",-1);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",-1);' />" ;
					uploadForm_atrr_2_1.setValue(html+html2) ;
					continue ;
				}
				obj[p] = record.data[p]; 
			}
			uploadForm.getForm().setValues(obj);
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}
		
	}
	
	function onUploadSave(){
		if (uploadForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		
		uploadForm.getForm().submit(
						{
							url:"upload",
							success : showUploadSuccessInfo,
							failure : showFormSubmitFailureInfo,
							waitMsg : "正在执行保存操作...",
							waitTitle : "请稍后..."
						});
	}
	
	function showUploadSuccessInfo(form, action){
		if (action.result.msg) {
			Ext.Msg.alert("提示",action.result.msg);
		} else {
			Ext.Msg.alert("提示","上传成功");
			uploadWin.hide();
			gridStore.reload();
		}
	}
	
	

	
	/******************end upload*************************/
	
	//上传缩略图
	
	var uploadZoomForm = new Ext.form.FormPanel({
		region : "center",
		frame : true,
		fileUpload : true,
		method : "POST",
		items : [{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textfield',
										inputType : "file",
										name : "zoomImg1",
										fieldLabel : "缩略图1"
									},{
										xtype : 'hidden',
										name:"id"
									},{
										xtype : 'hidden',
										name:"categoryId"
									}]
					       },{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
					    		   		id:"d_zoomImg1",
					    		   		xtype : 'displayfield'
									}]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textfield',
										inputType : "file",
										name : "zoomImg2",
										fieldLabel : "缩略图2"
									}]
					       },{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
					    		   		id:"d_zoomImg2",
					    		   		xtype : 'displayfield'
									}]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textfield',
										inputType : "file",
										name : "zoomImg3",
										fieldLabel : "缩略图3"
									}]
					       },{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
					    		   		id:"d_zoomImg3",
					    		   		xtype : 'displayfield'
									}]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textfield',
										inputType : "file",
										name : "zoomImg4",
										fieldLabel : "缩略图4"
									}]
					       },{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
					    		   		id:"d_zoomImg4",
					    		   		xtype : 'displayfield'
									}]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textfield',
										inputType : "file",
										name : "zoomImg5",
										fieldLabel : "缩略图5"
									}]
					       },{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
					    		   		id:"d_zoomImg5",
					    		   		xtype : 'displayfield'
									}]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textfield',
										inputType : "file",
										name : "zoomImg6",
										fieldLabel : "缩略图6"
									}]
					       },{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
					    		   		id:"d_zoomImg6",
					    		   		xtype : 'displayfield'
									}]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textfield',
										inputType : "file",
										name : "zoomImg7",
										fieldLabel : "缩略图7"
									}]
					       },{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
					    		   		id:"d_zoomImg7",
					    		   		xtype : 'displayfield'
									}]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textfield',
										inputType : "file",
										name : "zoomImg8",
										fieldLabel : "缩略图8"
									}]
					       },{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
					    		   		id:"d_zoomImg8",
					    		   		xtype : 'displayfield'
									}]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textfield',
										inputType : "file",
										name : "zoomImg9",
										fieldLabel : "缩略图9"
									}]
					       },{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
					    		   		id:"d_zoomImg9",
					    		   		xtype : 'displayfield'
									}]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textfield',
										inputType : "file",
										name : "zoomImg10",
										fieldLabel : "缩略图10"
									}]
					       },{
					    	   xtype:"panel",
					    	   columnWidth : 0.5,
					    	   layout : "form",
					    	   items:[{
					    		   		id:"d_zoomImg10",
					    		   		xtype : 'displayfield'
									}]
					       }]
				}]
	});
	
	var uploadZoomWin = new Ext.Window({
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
				 uploadZoomForm.getForm().reset();
			 }
			},
		tbar : [new Ext.Button({text : "保存",iconCls:'save',handler : onUploadZoomSave})],
		items :  [uploadZoomForm]
	});
	//上传缩略图
	function onUploadZoom(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			uploadZoomWin.setTitle("上传缩略图<font color='red'>尺寸200X200</font");
			uploadZoomWin.show() ;
			uploadZoomForm.getForm().reset();		
			var obj = {};
			for (p in record.data) {
				if(p == 'zoomImg1' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",101);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",101);' />" ;
					Ext.getCmp("d_zoomImg1").setValue(html+html2) ;
					continue ;
				}
				if(p == 'zoomImg2' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",102);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",102);' />" ;
					Ext.getCmp("d_zoomImg2").setValue(html+html2) ;
					continue ;
				}
				if(p == 'zoomImg3' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",103);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",103);' />" ;
					Ext.getCmp("d_zoomImg3").setValue(html+html2) ;
					continue ;
				}
				if(p == 'zoomImg4' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",104);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",104);' />" ;
					Ext.getCmp("d_zoomImg4").setValue(html+html2) ;
					continue ;
				}
				if(p == 'zoomImg5' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",105);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",105);' />" ;
					Ext.getCmp("d_zoomImg5").setValue(html+html2) ;
					continue ;
				}
				if(p == 'zoomImg6' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",106);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",106);' />" ;
					Ext.getCmp("d_zoomImg6").setValue(html+html2) ;
					continue ;
				}
				if(p == 'zoomImg7' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",107);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",107);' />" ;
					Ext.getCmp("d_zoomImg7").setValue(html+html2) ;
					continue ;
				}
				if(p == 'zoomImg8' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",108);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",108);' />" ;
					Ext.getCmp("d_zoomImg8").setValue(html+html2) ;
					continue ;
				}
				if(p == 'zoomImg9' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",109);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",109);' />" ;
					Ext.getCmp("d_zoomImg9").setValue(html+html2) ;
					continue ;
				}
				if(p == 'zoomImg10' && record.data[p] != ''){
					var html = "<a href=\'javascript:downloadImg("+record.data.id+",110);\'>"+record.data[p]+"</a>" ;
					var html2 = "&nbsp;&nbsp;<input type='button' value='delete' onClick='javascript:deleteImg("+record.data.id+",110);' />" ;
					Ext.getCmp("d_zoomImg10").setValue(html+html2) ;
					continue ;
				}

				obj[p] = record.data[p]; 
			}
			uploadZoomForm.getForm().setValues(obj);
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}
		
	}
	//保存缩略图
	function onUploadZoomSave(){
		if (uploadZoomForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		
		uploadZoomForm.getForm().submit(
						{
							url:"zoomUpload",
							success : showUploadZoomSuccessInfo,
							failure : showFormSubmitFailureInfo,
							waitMsg : "正在执行保存操作...",
							waitTitle : "请稍后..."
						});
	}
	function showUploadZoomSuccessInfo(form, action){
		if (action.result.msg) {
			Ext.Msg.alert("提示",action.result.msg);
		} else {
			Ext.Msg.alert("提示","上传成功");
			uploadZoomWin.hide();
			gridStore.reload();
		}
	}
	/********************************************************/
	
	//操作类型变量
	var operator_type ;
	//新增
	function onAdd(){
		operator_type = 'add';
		editWin.setTitle("新增");
		editWin.show();
		editForm.getForm().reset();
		
		colorGridPanel.getStore().removeAll();
		sizeGridPanel.getStore().removeAll();
		kvGridPanel.getStore().removeAll();
	}
	// 修改
	function onUpdate() {
		operator_type = 'update';
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			editWin.setTitle("修改");
			editWin.show();
			var obj = {};
			for (p in record.data) {
				if(p == 'purchaseTm' && record.data[p] != ''){
					obj[p] = formatDate(new Date(record.data.purchaseTm.time),"y-m-d") ;
					continue ;
				}
				obj[p] = record.data[p]; 
			}
			
			editForm.getForm().setValues(obj);
			//加载颜色列表
			colorGridPanel.getStore().baseParams['productId'] = record.data.id ;
			colorGridPanel.getStore().load() ;
			//加载尺寸列表
			sizeGridPanel.getStore().baseParams['productId'] = record.data.id ;
			sizeGridPanel.getStore().load() ;
			//加载特性值列表
			kvGridPanel.getStore().baseParams['productId'] = record.data.id ;
			kvGridPanel.getStore().load() ;
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时修改多条数据");
		}

	}
	function onSaveOrUpdate(){
		if (editForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		var waitMsg;
		if (operator_type == 'add') {
			waitMsg = "正在执行保存操作...";
			editForm.getForm().url = "add";
		} else if (operator_type == 'update') {
			waitMsg = "正在执行修改操作...";
			editForm.getForm().url = "update";
		}
		//组装颜色params
		var colorParams ;
		var colorRecords = colorGridStore.getRange() ;
		for(var i = 0 ; i < colorRecords.length ; i++){
			var r = colorRecords[i] ;
			if(colorRecords.length == 1){
				colorParams = "[{key:'"+r.data.key+"',price:'"+r.data.price+"'}]" ;
				continue ;
			}
			if(i==0){
				colorParams = "[{key:'"+r.data.key+"',price:'"+r.data.price+"'}" ;
			}else if(i == colorRecords.length-1){
				colorParams += ",{key:'"+r.data.key+"',price:'"+r.data.price+"'}]" ;
			}else{
				colorParams += ",{key:'"+r.data.key+"',price:'"+r.data.price+"'}" ;
			}
		}

		//组装尺寸params
		var sizeParams ;
		var sizeRecords = sizeGridStore.getRange() ;
		for(var i = 0 ; i < sizeRecords.length ; i++){
			var r = sizeRecords[i] ;
			if(sizeRecords.length == 1){
				sizeParams = "[{key:'"+r.data.key+"',price:'"+r.data.price+"'}]" ;
				continue ;
			}
			if(i==0){
				sizeParams = "[{key:'"+r.data.key+"',price:'"+r.data.price+"'}" ;
			}else if(i == sizeRecords.length-1){
				sizeParams += ",{key:'"+r.data.key+"',price:'"+r.data.price+"'}]" ;
			}else{
				sizeParams += ",{key:'"+r.data.key+"',price:'"+r.data.price+"'}" ;
			}
		}
		//组装特性值params
		var kvParams ;
		var kvRecords = kvGridStore.getRange() ;
		for(var i = 0 ; i < kvRecords.length ; i++){
			var r = kvRecords[i] ;
			if(kvRecords.length == 1){
				kvParams = "[{key:'"+r.data.key+"',value:'"+r.data.value+"',sort:'"+r.data.sort+"'}]" ;
				continue ;
			}
			if(i==0){
				kvParams = "[{key:'"+r.data.key+"',value:'"+r.data.value+"',sort:'"+r.data.sort+"'}" ;
			}else if(i == kvRecords.length-1){
				kvParams += ",{key:'"+r.data.key+"',value:'"+r.data.value+"',sort:'"+r.data.sort+"'}]" ;
			}else{
				kvParams += ",{key:'"+r.data.key+"',value:'"+r.data.value+"',sort:'"+r.data.sort+"'}" ;
			}
		}
		

		editForm.getForm().submit(
						{	params:{'colorParams':colorParams,'kvParams':kvParams,'sizeParams':sizeParams},
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
			if (operator_type == 'add') {
				Ext.Msg.alert("提示","保存成功");
			} else if (operator_type == 'update') {
				Ext.Msg.alert("提示","更新成功");
			}
			editWin.hide();
			gridStore.reload();
		}
	}
	
	function onDelete(){
		var records = gridPanel.getSelectionModel().getSelections();
		if (records.length < 1) {
			Ext.MessageBox.alert('提示', '选择需要删除的数据');
		} else {
			Ext.MessageBox.confirm('提示','确定要删除选中的记录吗', deleteRecord);
		}
	}
	// 删除事件请求调用
	function deleteRecord(result) {
			if (result == 'yes') {
				var records = gridPanel.getSelectionModel().getSelections();
				var ids = '';
				for(var i = 0; i < records.length; i++) {
					if(i==0){
						ids += records[i].data.id ;
					}else{
						ids += ',' + records[i].data.id ;
					}
				}
				Ext.Ajax.request({params: {ids: ids},
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
	/***************设置排序******************/
	var sortForm = new Ext.form.FormPanel({region:"center",height:150,frame:true,fileUpload:false,autoWidth:true,timeout:300
		,items: [
			new Ext.Panel({layout:"column",
				items: [{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{	
									xtype:"numberfield",
									width:150,
									name:"sortNo",
									allowBlank:false,
									fieldLabel:"排序"}
									,{
										xtype:"hidden",
										name:"id"
									}]
						}]
			})
		]
	});
	
	var sortWin = new Ext.Window({height:150,constrainHeader:true,width:300,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,tbar: [
		        new Ext.Button({text:"保存",iconCls:'save',handler:sortSave})
		]
		,items: [
		         sortForm
		]
	});
	function sortSave(){
		if (sortForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		
		sortForm.getForm().submit({
					url:"updateSortNo",
					success : function(form, action){
						if (action.result.msg) {
							Ext.Msg.alert("提示",action.result.msg);
						} else {
								Ext.Msg.alert("提示","操作成功");
								sortWin.hide();
								gridStore.reload();
						}
					},
					failure : showFormSubmitFailureInfo,
					waitMsg : "正在执行...",
					waitTitle : "请稍后..."
				});
	}
	function onSort(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			sortWin.setTitle("设置排序（<font color='red'>说明：排序越小，越靠前</font>）");
			sortWin.show();
			var obj = {};
			for (p in record.data) {
				obj[p] = record.data[p]; 
			}
			sortForm.getForm().setValues(obj);
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}
	}
	
	/*****************编辑产品详情******************/
	var descForm = new Ext.form.FormPanel({region:"center",height:300,autoScroll:true,frame:true,fileUpload:false,autoWidth:true,timeout:300
		,items: [
			new Ext.Panel({layout:"column"
				,items: [
					new Ext.Panel({id:"productDescPanel",width:1000,layout:"form",items: []})
				]
			})
			,new Ext.Panel({layout:"column"
				,items: [
							{
								xtype:"hidden",
								name:"id"
							}
						]
					})
		]
	});
	// 编辑详情窗口
	var descWin = new Ext.Window({height:600,constrainHeader:true,width:1020,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,tbar: [
		        new Ext.Button({text:"保存",iconCls:'save',handler:onEditDescSave })
		]
		,items: [
		         descForm
		]
	});
	
	
	//初始化编辑器
	var gridForm = new Ext.Panel({
	        frame: true,
	        labelAlign: 'top',
	        bodyStyle:'padding:5px',
	        width: 950,
	        trackResetOnLoad: true, // save value when data is loaded
	        layout: 'column',    // Specifies that the items will now be arranged in columns
	        items: [{
	                xtype: 'ckeditor',
	                id:'ckeditor-id',
	                fieldLabel: 'Editor',
	                name: 'desc',
					CKConfig: {
						customConfig : '../ckeditor/config.js',
						height : 350,
						width: 950
							  }
	            	}]
	    });
	
	 var productDescPanel = Ext.getCmp("productDescPanel") ;
	 productDescPanel.add(gridForm);
	 productDescPanel.doLayout();
	 
	 function onEditDesc(){
		 var records = gridPanel.getSelectionModel().getSelections();
			var record = gridPanel.getSelectionModel().getSelected();
			if (records.length == 1) {
				descWin.setTitle("编辑产品详情<font color='red'>(产品编号："+record.data.productNo+")</font>");
				descWin.show();
				var obj = {};
				for (p in record.data) {
					obj[p] = record.data[p]; 
				}
				descForm.getForm().setValues(obj);
				
			} else if (records.length < 1) {
				Ext.Msg.alert("提示","请选择一笔数据行");
			} else if (records.length > 1) {
				Ext.Msg.alert("提示","不能同时操作多条数据");
			}
	 }
	 function onEditDescSave(){
		 descForm.getForm().submit({
				url:"updateDesc",
				success : function(form, action){
					if (action.result.msg) {
						Ext.Msg.alert("提示",action.result.msg);
					} else {
							Ext.Msg.alert("提示","操作成功");
							descWin.hide();
							gridStore.reload();
					}
				},
				failure : showFormSubmitFailureInfo,
				waitMsg : "正在执行...",
				waitTitle : "请稍后..."
			});
	 }
});

