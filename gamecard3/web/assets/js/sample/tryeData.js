//<%@ page language="java" contentType="text/html; charset=UTF-8"%>
Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	Ext.BLANK_IMAGE_URL='../ext-2.0.2/resources/images/default/s.gif';
	
	var gridStore = new Ext.data.JsonStore({
		url:'listTryeDatas.action',
		fields:[{name: 'id'},
		        {name:'tryeBreed'},
		        {name:'tryeModel'},
		        {name:'tryePrice'},
		        {name:'tryeBuyCode'},
		        {name:'tryeVacual'},
		        {name:'valid'},
		        {name:'minMiles'}
		        ],
		totalProperty:'totalSize',
		root:'root'
	});
	
	
	
	var queryForm = new Ext.form.FormPanel({
		frame:true,
		region:"north",
		autoHeight:true,
		id:'queryForm',
		items: [

			new Ext.form.FieldSet({
				labelWidth:80,
				height:110,
				layout:"column",
				title:"${app:i18n_def('vmsoperation.tryeData.page.1','查询条件')}",
				layout:'column',
				items:[
				       {	
				    	   xtype:"panel",
				    	   columnWidth:.4,
				    	   labelWidth:70,
				    	   labelAlign:"right",
				    	   layout:"form",
				    	   items:{
				    		   xtype:"combo",
				    		   id:"tryeBreed",
				    		   hiddenName:"tryeData.tryeBreed",
				    		   fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.2','轮胎品牌')}',
				    		   typeAhead: true,
				    		   mode: 'remote',
				    		   width:120,
				    		   editable:false,
				    		   displayField:"text",
				    		   valueField:"key",
				    		   triggerAction: 'all',
				    		   emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
				    		   selectOnFocus:true,
				    		   store:new Ext.data.JsonStore({
				    			   	autoLoad:true,
				    			    url:'listTypeBreed.action',
				    				fields:[{name: 'key',mapping:'TEXT'},
				    				        {name:'text',mapping:'TEXT'}
				    				        ],
				    				root:'tryeBreedList',
				    				listeners:{
				    					load:function(t){
				    						  var all = new Ext.data.Record([]);
				    						  all.set('key', '');
				    		                  all.set('text', '${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}');
				    		                  t.insert(0, [all]);
				    					}
				    				}
				    		   }),
				    		   listeners:{
				    			   'beforequery': function(qe){
										//每次都查询
										delete qe.combo.lastQuery;  
									},
				    			   select : function(){
										Ext.getCmp("tryeModel").setValue('');
										Ext.getCmp("tryeModel").store.removeAll();
										Ext.getCmp("tryeModel").store.load();
										
									},
				    			   render:function(f){
				    			   var el = f.getEl().dom;
				    			   var htmlScript = "<font color=blue id='addTryeBreedBtn' style='cursor:hand;font-size:12px;margin-left:2em;'"
				    				   +" onmouseover='this.style.color=\"red\";' onmouseout='this.style.color=\"blue\";'>${app:i18n_def('vmsoperation.tryeData.page.4','[新增品牌]')}</font>";
				    			   Ext.DomHelper.insertAfter(el.nextSibling,htmlScript);
				    			   Ext.get("addTryeBreedBtn").on("click",function(){
				    				   Ext.Msg.prompt('${app:i18n_def('vmsoperation.tryeData.page.5','新增品牌')}','${app:i18n_def('vmsoperation.tryeData.page.6','请输入品牌名称(')}<font color=red>${app:i18n_def('vmsoperation.tryeData.page.7','※')}</font>)',function(btn,newText){
				    					   if( "ok"==btn && !Ext.isEmpty(newText) ){
				    						   if(newText.trim() == ''){
				    							   Ext.MessageBox.alert('${app:i18n_def('vmsoperation.tryeData.page.8','提示')}', '${app:i18n_def('vmsoperation.tryeData.page.45','轮胎品牌不能为空')}');
				    							   return ;
				    						   }
				    						   if(newText.trim().length > 50){
				    							   Ext.MessageBox.alert('${app:i18n_def('vmsoperation.tryeData.page.8','提示')}', '${app:i18n_def('vmsoperation.tryeData.page.48','品牌名称最大长度为50个汉字')}');
				    							   return ;
				    						   }
				    						   Ext.Ajax.request({params: {'tryeData.tryeBreed': newText},
				    								url: "addTryeBreed.action",
				    								success: function(response) {
				    									var resp = Ext.util.JSON.decode(response.responseText);
				    									if(resp && resp.msg){
				    										Ext.MessageBox.alert('${app:i18n_def('vmsoperation.tryeData.page.8','提示')}', resp.msg);
				    									}else if (resp.success) {
				    										Ext.MessageBox.alert('${app:i18n_def('vmsoperation.tryeData.page.8','提示')}', '${app:i18n_def('vmsoperation.tryeData.page.add.breed.success','新增品牌成功')}');
				    										//var p = new f.store.recordType({key:f.store.getCount(),text:newText});
								    						//f.store.insert(0,p);
				    									} else {
				    										Ext.MessageBox.alert('${app:i18n_def('vmsoperation.tryeData.page.9','新增品牌失败')}', resp.status);
				    									}
				    								},
				    								failure:ajaxRequestFailure
				    							});
				    						   
				    						   
				    					   }
				    				   });
				    			   }); // end addTryeBreedBtn.click
				    		   }}
				    	   }
				       },
				       {
				    	   xtype:"panel",
				    	   columnWidth:.3,
				    	   labelWidth:80,
				    	   labelAlign:"right",
				    	   layout:"form",
				    	   items:{
				    		 	xtype:"combo",
				    		   	id:"tryeModel",
								hiddenName:"tryeData.tryeModel",
								fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.10','轮胎型号')}',
								//labelWidth:100,
								typeAhead: true,
								mode: 'remote',
								editable: false,
								displayField:"text",
								valueField:"key",
								triggerAction: 'all',
								emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
								selectOnFocus:true,
								width:120,
								store:new Ext.data.JsonStore({
									autoLoad:true,
									url:'listTryeModelForCombox.action',
									fields:[{name: 'key', mapping:'tryeModel'},
									        {name: 'text', mapping:'tryeModel'}],
									root:'root',
									listeners:{
										beforeload:function(s){
											s.baseParams["tryeData.tryeBreed"] = Ext.getCmp('tryeBreed').getValue();
									  	},
									  	load:function(t){
				    						  var all = new Ext.data.Record([]);
				    						  all.set('key', '');
				    		                  all.set('text', '${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}');
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
				    		 
				    	   		}
				       },
				       {
				    	   xtype:"panel",
				    	   columnWidth:.3,
				    	   labelWidth:80,
				    	   labelAlign:"right",
				    	   layout:"form",
				    	   items:{
				    		   xtype:"textfield",
				    		   name:"tryeData.tryeBuyCode",
				    		   maxLength:33,
				    		   fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.11','统购编码')}'
				    	   		}
				       },
				       {	xtype:"panel",
				    	   columnWidth:.4,
				    	   labelWidth:70,
				    	   labelAlign:"right",
				    	   layout:"form",
				    	   items:{xtype:"combo",
									hiddenName:"tryeData.tryeVacual",
									fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.12','是否真空胎')}',
									typeAhead: true,
									mode: 'local',
									width:120,
									editable:false,
									displayField:"text",
									valueField:"key",
									triggerAction: 'all',
									emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
									selectOnFocus:true,
									store:new Ext.data.SimpleStore({
										fields:["key","text"],
										data:[['','${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}'],['1','${app:i18n_def('vmsoperation.tryeData.page.13','是')}'],['0','${app:i18n_def('vmsoperation.tryeData.page.14','否')}']]
									})
				       		}
				       },
				       {	xtype:"panel",
				    	   	columnWidth:.3,
				    	   	labelWidth:80,
				    	   	labelAlign:"right",
				    	   	layout:"form",
				    	   	items:{xtype:"combo",
						    	   	hiddenName:"tryeData.valid",
									fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.15','是否有效')}',
									typeAhead: true,
									mode: 'local',
									width:120,
									editable:false,
									displayField:"text",
									valueField:"key",
									triggerAction: 'all',
									emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
									selectOnFocus:true,
									store:new Ext.data.SimpleStore({
										fields:["key","text"],
										data:[['','${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}'],['1','${app:i18n_def('vmsoperation.tryeData.page.13','是')}'],['0','${app:i18n_def('vmsoperation.tryeData.page.14','否')}']]
									})
				    	   		}
				       }
				       
				       
				       ]
				})
	]});
	
	
	function checkboxRenderer(v, p, r) {
		var trueValue = eval(this.id + '.trueValue');
		p.css += ' x-grid3-check-col-td';
		return '<div class="x-grid3-check-col'+((v==trueValue)?'-on':'')+' x-grid3-cc-'+this.id+'">&#160;</div>';
	}


	// 日期显示格式
	function datetimeRenderer(v, p, r) {
		if ((v == null) || (v.length < 19)) return v;
		var dateFormat = eval(this.id + '.format');
		if (typeof(v) == "string") {
			v = v.replace('T', ' ' );
			v = Date.parseDate(v, "Y-m-d H:i:s");
		}
		return v.format(dateFormat);
	}

	var YorN = new Ext.form.ComboBox({
		typeAhead: true,
		lazyRender:true,
		width:150,
		mode:"local",
		valueField:"key",
		displayField:"text",
		// hiddenName:"tryeData.valid",
		triggerAction:"all",
		store:new Ext.data.SimpleStore({
			data: [['','${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}'],['1','${app:i18n_def('vmsoperation.tryeData.page.13','是')}'],['0','${app:i18n_def('vmsoperation.tryeData.page.14','否')}']],
			fields: ['key','text']}),
		listeners:{
			'select':function(obj){
				// console.log(obj);
				// Ext.Msg.alert('select',obj.value) ;
			}
			
		}});
	// 下拉框处理函数
	function comboboxRenderer(v, p, r) {
		if (v != null) {
			var records = eval(this.id + '.initialConfig.store.getRange()');
			var keyField = eval(this.id + '.valueField');
			var displayField = eval(this.id + '.displayField');
			for(var i = 0; i < records.length; i ++) {
				var record = records[i];
				if (record.get(keyField) == v) {
					return record.get(displayField);
				}
			}
		}
		return v;
	}

	
	var pagingBar = new Ext.PagingToolbar({
		displayInfo:true,
		displayMsg:"${app:i18n_def('vmsoperation.tryeData.page.16','当前显示 {0} - {1} 总记录数目 {2}')}",
		store:gridStore,
		pageSize:20,
		emptyMsg:"${app:i18n_def('vmsoperation.tryeData.page.17','未检索到数据')}"});
	var checkboxSelectionMode  = new Ext.grid.CheckboxSelectionModel({});
	var gridPanel = new Ext.grid.GridPanel(
			{
			region:'center',
			tbar:pagingBar,
			store:gridStore,
			sm: checkboxSelectionMode,
			// stripeRows : true,
			cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
			                              checkboxSelectionMode,
			                          	{header:'${app:i18n_def('vmsoperation.tryeData.page.2','轮胎品牌')}',dataIndex:"tryeBreed",sortable:false},
			                          	{header:'${app:i18n_def('vmsoperation.tryeData.page.10','轮胎型号')}',dataIndex:"tryeModel",sortable:false},
			                          	{header:'${app:i18n_def('vmsoperation.tryeData.page.18','轮胎价格')}',dataIndex:"tryePrice",sortable:false},
			                          	{header:'${app:i18n_def('vmsoperation.tryeData.page.11','统购编码')}',dataIndex:"tryeBuyCode",sortable:false},
			                          	{header:'${app:i18n_def('vmsoperation.tryeData.page.12','是否真空胎')}',dataIndex:"tryeVacual",sortable:false,renderer:comboboxRenderer,id:"YorN"},
			                          	{header:'${app:i18n_def('vmsoperation.tryeData.page.15','是否有效')}',dataIndex:"valid",sortable:false,renderer:comboboxRenderer,id:"YorN"},
			                          	{header:'${app:i18n_def('vmsoperation.tryeData.page.19','最低标准里程(km)')}',dataIndex:"minMiles",sortable:false}
			                              ])
	});
	
	var btnSearch = new Ext.Button({
		text:"${app:i18n_def('vmsoperation.tryeData.page.20','查询')}",
		cls:'x-btn-normal', 
		minWidth:60,
		handler:onSearch
	});
	
	var btnDetail = new Ext.Button({
		text:"${app:i18n_def('vmsoperation.tryeData.page.21','查看详细')}",
		cls:'x-btn-normal', 
		minWidth:60,
		handler:onDetail
	});
	var btnAdd = new Ext.Button({
		text:"${app:i18n_def('vmsoperation.tryeData.page.22','增加')}",
		cls:'x-btn-normal', 
		minWidth:60,
		handler:onAdd
	});
	var btnUpdate = new Ext.Button({
		text:"${app:i18n_def('vmsoperation.tryeData.page.23','修改')}",
		cls:'x-btn-normal', 
		minWidth:60,
		handler:onUpdate
	});
	
	var btnSave = new Ext.Button({
		text:"${app:i18n_def('vmsoperation.tryeData.page.24','保存')}",
		handler:onSaveOrUpdate
	});
	
	var btnCancel = new Ext.Button({
		text:"${app:i18n_def('vmsoperation.tryeData.page.25','取消')}",
		handler:onCancel
	});
	
	var treeLoader = new Ext.tree.TreeLoader({
		dataUrl:"../vmscommon/deptTree.action?textField=deptName&idField=id&leafField=&clsField=&childrenField="
			});
	
	
	new Ext.Viewport({
		layout:'border',
		items:[{
			region:'center',
			tbar:['-'
			      <app:isPermission code="/vmsoperation/listTryeDatas.action">,btnSearch</app:isPermission>
			      ,btnDetail
			      <app:isPermission code="/vmsoperation/saveTryeData.action">,btnAdd</app:isPermission>
			      <app:isPermission code="/vmsoperation/updateTryeData.action">,btnUpdate</app:isPermission>],
			layout:'border',
			// bodyBorder:false,

			items:[
			       queryForm,
			       gridPanel
			       ]
		}]
	});
	
	
	var editForm = new Ext.form.FormPanel({
		 width:500,
		 height:300,
		// autoHeight:true,
		modal:true,
		border:false,
		bodyBorder:false,
		closable:false,
		resizable:false,
		layout:'fit',
		closeAction:'hide',
		items:{xtype:'panel',
			 height:23*17+17,
			layout:'column',
			frame:true,
			items:[{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{xtype:"combo",
								hiddenName:"tryeData.tryeBreed",
								fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.2','轮胎品牌')}',
								typeAhead: true,
								mode: 'remote',
								width:120,
								allowBlank:false,
								editable:false,
								labelSeparator:'<font color=red>*</font>',
								displayField:"text",
								valueField:"text",
								triggerAction: 'all',
								emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
								selectOnFocus:true,
								store:new Ext.data.JsonStore({
					    			   	autoLoad:true,
					    			    url:'listTypeBreed.action',
					    				fields:[{name: 'key',mapping:'KEY'},
					    				        {name:'text',mapping:'TEXT'}
					    				        ],
					    				root:'tryeBreedList'
					    		   }),
									listeners:{
									'beforequery': function(qe){
											//每次都查询
											delete qe.combo.lastQuery;  
										},
									'select':function(obj){
										if(obj.value == '${app:i18n_def('vmsoperation.tryeData.page.26','其他')}'){
											btnSave.disable() ;
										}else{
											btnSave.enable() ;
										}
										
									}
									
								}
						}
					},{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{
							xtype:'textfield',
							allowBlank:false,
							name:'tryeData.tryeModel',
							fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.10','轮胎型号')}',
							maxLength:33,
							width:120,
							labelSeparator:'<font color=red>*</font>'
						}
					},{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{
							xtype:'textfield',
							allowBlank:false,
							name:'tryeData.tryeBuyCode',
							fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.11','统购编码')}',
							width:120,
							maxLength:33,
							labelSeparator:'<font color=red>*</font>'
						}
					},{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{
							xtype:'numberfield',
							allowBlank:false,
							allowNegative:false,
							name:'tryeData.tryePrice',
							fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.18','轮胎价格')}',
							maxLength:9,
							minValue:0,
							maxValue:4000,
							width:120,
							labelSeparator:'<font color=red>*</font>'
						}
					},{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{xtype:"combo",
								hiddenName:"tryeData.tryeVacual",
								fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.12','是否真空胎')}',
								typeAhead: true,
								mode: 'local',
								width:120,
								allowBlank:false,
								editable:false,
								displayField:"text",
								valueField:"key",
								triggerAction: 'all',
								emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
								selectOnFocus:true,
								store:new Ext.data.SimpleStore({
										fields:["key","text"],
										data:[['','${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}'],['1','${app:i18n_def('vmsoperation.tryeData.page.13','是')}'],['0','${app:i18n_def('vmsoperation.tryeData.page.14','否')}']]
									}),
								labelSeparator:'<font color=red>*</font>'
							}
						},{xtype:'panel',
							layout:'form',
							labelWidth:110,
							labelAlign:'right',
							columnWidth:.5,
							items:{xtype:"combo",
									hiddenName:"tryeData.valid",
									fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.15','是否有效')}',
									typeAhead: true,
									mode: 'local',
									width:120,
									allowBlank:false,
									editable:false,
									displayField:"text",
									valueField:"key",
									triggerAction: 'all',
									emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
									selectOnFocus:true,
									store:new Ext.data.SimpleStore({
											fields:["key","text"],
											data:[['','${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}'],['1','${app:i18n_def('vmsoperation.tryeData.page.13','是')}'],['0','${app:i18n_def('vmsoperation.tryeData.page.14','否')}']]
										}),
									labelSeparator:'<font color=red>*</font>'
							}
					},{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{
								xtype:'numberfield',
								allowBlank:false,
								allowNegative:false,
								name:'tryeData.minMiles',
								fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.19','最低标准里程(km)')}',
								maxLength:9,
								width:120,
								minValue:0,
								decimalPrecision:0,
								labelSeparator:'<font color=red>*</font>'
						}
					}
			]
		}
	});
	
	var editWin = new Ext.Window({
		frame:true,
		width:550,
		height:310,
		layout:"fit",
		modal:true,
		closeAction:"hide",
		buttonAlign:'right',
		tbar:[
		         btnSave,
		         btnCancel
				 ],
		items: [
				editForm
				]
	});
	
	var detailForm = new Ext.form.FormPanel({
		 width:500,
		 height:300,
		// autoHeight:true,
		modal:true,
		border:false,
		bodyBorder:false,
		closable:false,
		resizable:false,
		layout:'fit',
		closeAction:'hide',
		items:{xtype:'panel',
			 height:23*17+17,
			layout:'column',
			frame:true,
			items:[{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{xtype:"combo",
								hiddenName:"tryeData.tryeBreed",
								fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.2','轮胎品牌')}',
								typeAhead: true,
								mode: 'remote',
								width:120,
								disabled:true,
								readOnly:true,
								editable:false,
								displayField:"text",
								valueField:"text",
								triggerAction: 'all',
								emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
								selectOnFocus:true,
								store:new Ext.data.JsonStore({
					    			   	autoLoad:true,
					    			    url:'listTypeBreed.action',
					    				fields:[{name: 'key',mapping:'KEY'},
					    				        {name:'text',mapping:'TEXT'}
					    				        ],
					    				root:'tryeBreedList'
					    		   }),
									listeners:{
									'select':function(obj){
										if(obj.value == '${app:i18n_def('vmsoperation.tryeData.page.26','其他')}'){
											btnSave.disable() ;
										}else{
											btnSave.enable() ;
										}
										
									}
									
								}
						}
					},{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{
							xtype:'textfield',
							readOnly:true,
							name:'tryeData.tryeModel',
							fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.10','轮胎型号')}',
							width:120
						}
					},{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{
							xtype:'textfield',
							readOnly:true,
							name:'tryeData.tryeBuyCode',
							fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.11','统购编码')}',
							width:120
						}
					},{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{
							xtype:'numberfield',
							allowNegative:false,
							readOnly:true,
							name:'tryeData.tryePrice',
							fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.18','轮胎价格')}',
							width:120
						}
					},{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{xtype:"combo",
								hiddenName:"tryeData.tryeVacual",
								fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.12','是否真空胎')}',
								typeAhead: true,
								mode: 'local',
								width:120,
								disabled:true,
								readOnly:true,
								editable:false,
								displayField:"text",
								valueField:"key",
								triggerAction: 'all',
								emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
								selectOnFocus:true,
								store:new Ext.data.SimpleStore({
										fields:["key","text"],
										data:[['','${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}'],['1','${app:i18n_def('vmsoperation.tryeData.page.13','是')}'],['0','${app:i18n_def('vmsoperation.tryeData.page.14','否')}']]
									})
							}
						},{xtype:'panel',
							layout:'form',
							labelWidth:110,
							labelAlign:'right',
							columnWidth:.5,
							items:{xtype:"combo",
									hiddenName:"tryeData.valid",
									fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.15','是否有效')}',
									typeAhead: true,
									mode: 'local',
									width:120,
									disabled:true,
									readOnly:true,
									editable:false,
									displayField:"text",
									valueField:"key",
									triggerAction: 'all',
									emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
									selectOnFocus:true,
									store:new Ext.data.SimpleStore({
											fields:["key","text"],
											data:[['','${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}'],['1','${app:i18n_def('vmsoperation.tryeData.page.13','是')}'],['0','${app:i18n_def('vmsoperation.tryeData.page.14','否')}']]
										})
							}
					},{xtype:'panel',
						layout:'form',
						labelWidth:110,
						labelAlign:'right',
						columnWidth:.5,
						items:{
								xtype:'numberfield',
								allowNegative:false,
								readOnly:true,
								name:'tryeData.minMiles',
								fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.19','最低标准里程(km)')}',
								width:120
						}
					}
			]
		}
	});
	
	var detailWin = new Ext.Window({
		frame:true,
		width:550,
		height:310,
		layout:"fit",
		modal:true,
		closeAction:"hide",
		buttonAlign:'right',
		tbar : [ {
			xtype : "button",
			text : "${app:i18n_def('vmsoperation.tryeData.page.24','保存')}",
			disabled : true
		}, {
			xtype : "button",
			text : "${app:i18n_def('vmsoperation.tryeData.page.25','取消')}",
			handler : function() {
				detailWin.hide();
				detailForm.getForm().reset();
			}
		} ],
		items: [
		        detailForm
				]
	});
	function onSearch() {
		if (queryForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.27','数据校验错误！')}");
		} else {
			gridPanel.getStore().baseParams = queryForm.getForm().getValues();
			gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
			gridPanel.getStore().load();
		}
	}
	function onAdd() {
		operator_type = 'add' ;
		editWin.setTitle("${app:i18n_def('vmsoperation.tryeData.page.22','增加')}") ;
		editWin.show() ;
		btnSave.enable() ;
		btnCancel.enable();
		editForm.getForm().findField("tryeData.tryeBreed").setDisabled(false);
		editForm.getForm().findField("tryeData.tryeModel").setDisabled(false);
		editForm.getForm().findField("tryeData.tryeBuyCode").setDisabled(false);
		editForm.getForm().reset() ;
	}
	var update_id ;
	function onUpdate() {
		operator_type = 'update' ;	
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if(records.length ==1){
			editWin.setTitle("${app:i18n_def('vmsoperation.tryeData.page.23','修改')}") ;
			editWin.show() ;
			btnSave.enable() ;
			btnCancel.enable();
			var obj = {};
			for(p in record.data) {
				if(p == 'id'){
					update_id = record.data[p];
					continue ;
				}
				obj['tryeData.' + p] = record.data[p];
			}
			editForm.getForm().setValues(obj);
			editForm.getForm().findField("tryeData.tryeBreed").setDisabled(true);
			editForm.getForm().findField("tryeData.tryeModel").setDisabled(true);
			editForm.getForm().findField("tryeData.tryeBuyCode").setDisabled(true);
			if(record.data.tryeBreed == '${app:i18n_def('vmsoperation.tryeData.page.26','其他')}'){
				btnSave.disable();
			}
		}else if(records.length < 1){
			Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.28','请选择一条记录')}");
		}else if(records.length > 1){
			Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.29','不能同时修改多条数据')}");
		}
			
	}

	
	function onCancel() {
		editWin.hide() ;
	}
	
	function onSaveOrUpdate() {
		if (editForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.27','数据校验错误！')}");
			return ;
		} 
		var waitMsg ;
		if(operator_type == 'add'){
			//校验轮胎型号、统购编码是否为空串  
			if(editForm.getForm().findField('tryeData.tryeModel').getValue().trim() == ''){
				Ext.MessageBox.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.46','轮胎型号不能为空')}");
				return ;
			}
			if(editForm.getForm().findField('tryeData.tryeBuyCode').getValue().trim() == ''){
				Ext.MessageBox.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.47','统购编码不能为空')}");
				return ;
			}
			waitMsg = "${app:i18n_def('vmsoperation.tryeData.page.30','正在执行保存操作...')}" ;
			editForm.getForm().url = "saveTryeData.action" ;
		}else if(operator_type == 'update'){
			waitMsg = "${app:i18n_def('vmsoperation.tryeData.page.31','正在执行修改操作...')}" ;
			editForm.getForm().url = "updateTryeData.action?tryeData.id=" +update_id;
		}
		editForm.getForm().submit({
			success:showSaveOrUpdateSuccessInfo,
			failure:showSaveOrUpdateFailureInfo,
			waitMsg:waitMsg,
			waitTitle:"${app:i18n_def('vmsoperation.tryeData.page.32','请稍后...')}"
		});
	}
	
	function showSaveOrUpdateSuccessInfo(form, action){
		if (action.result.msg) {
			Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", action.result.msg);
		} else {
			if(operator_type == 'add'){
				Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.33','保存成功')}");
			}else if(operator_type == 'update'){
				Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.34','更新成功')}");
			}
			editWin.hide();
			gridStore.reload();							
		}
	}
	
	function showSaveOrUpdateFailureInfo(form, action){
		var msg = '';
		if (action && action.result && action.result.error) {
			Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.35','系统出现了异常,请与管理员联系!')}");
		} else {
			if (action.failureType === Ext.form.Action.CONNECT_FAILURE) {
				if (action.response.status == 0) {
					msg = "${app:i18n_def('vmsoperation.tryeData.page.36','连接失败,无法连接到服务器!')}";
				} else if (action.response.status == -1) {
					msg = "${app:i18n_def('vmsoperation.tryeData.page.37','服务器处理超时!')}";
				} else {
					msg = String.format("${app:i18n_def('vmsoperation.tryeData.page.38','错误代码:{0}, 错误描述:{1}!')}", action.response.status, action.response.statusText);
				}
			} else if (action.failureType === Ext.form.Action.CLIENT_INVALID) {
				msg = "${app:i18n_def('vmsoperation.tryeData.page.39','请将表单填写正确!')}";
			}
			Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", msg);
		}
	}
	
	function onDetail() {
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if(records.length ==1){
			detailWin.setTitle("${app:i18n_def('vmsoperation.tryeData.page.40','查看明细')}") ;
			detailWin.show() ;
			var obj = {};
			for(p in record.data) {
				obj['tryeData.' + p] = record.data[p];
			}
			detailForm.getForm().setValues(obj);
			//btnSave.disable();
			//btnCancel.disable();
		}else if(records.length < 1){
			Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.28','请选择一条记录')}");
		}else if(records.length > 1){
			Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.41','不能同时操作多条记录')}");
		}
	}
	
	// ajax请求失败处理事件
	function ajaxRequestFailure(response) {
		  if (response.status == 0) {
		      Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.42','无法连接到服务器，请检查网络是否正常')}");
		  } else if (response.status == -1) {
		      Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}", "${app:i18n_def('vmsoperation.tryeData.page.43','服务器处理超时，请稍后再试')}");
		  } else {
				Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeData.page.8','提示')}","${app:i18n_def('vmsoperation.tryeData.page.44','系统出现异常,请与管理员联系')}");
			}
		};
	
	

});