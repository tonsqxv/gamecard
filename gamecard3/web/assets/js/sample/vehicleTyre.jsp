<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="ext" uri="/ext-tags"%>
<%@ taglib prefix="app" uri="/app-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<ext:script before="true">
		<%@include file="/scripts/vmsoperation/vehicleCert.js"%>
		<%@include file="/scripts/vmsoperation/vendorWindows.js"%>
	</ext:script>
<ext:ui>
	<ext:script>
		// 日期显示格式
		var dispalyDateFormat = new Ext.form.DateField({
		maxLength:20,
		width:120,
		disabled:false,
		format:"Y-m-d"});
		function checkboxRenderer(v, p, r) {
		var trueValue = eval(this.id + '.trueValue');
		p.css += ' x-grid3-check-col-td';
		return '<div class="x-grid3-check-col'+((v==trueValue)?'-on':'')+' x-grid3-cc-'+this.id+'">&#160;</div>';
	}

	var tryeKind = new Ext.form.ComboBox({
		typeAhead: true,
		lazyRender:true,
		width:150,
		mode:"local",
		valueField:"key",
		displayField:"text",
		triggerAction:"all",
		store : new Ext.data.SimpleStore({
					data : [ [ '', '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}' ], [ '1', '${app:i18n_def('vmsoperation.tryeStoreManager.page.13','新胎')}' ], [ '2', '${app:i18n_def('vmsoperation.tryeStoreManager.page.14','旧胎')}' ] ],
					fields : [ 'key', 'text' ]
				})
		});
		
		
		function myTriggerClick(obj){
			var _curObj = obj;
			var tryeDlg = Ext.getCmp("tryeDlg");
			if( Ext.isEmpty(tryeDlg,false) ){
				var CK = new Ext.grid.CheckboxSelectionModel({singleSelect:true});
				
				var centerStore = new Ext.data.JsonStore({
					autoLoad:true,
    			    url:'listTryeStores.action?tryeStore.deptId='+query_deptId.getValue(),
    				fields:[{name: 'id'},
					        {name:'tryeBreed'},
					        {name:'tryeModel'},
					        {name:'tryeNo'},
					        {name:'tryeInTm'},
					        {name:'tryeKind'},
					        {name:'usedMile'},
					        {name:'checkEmp'},
					        {name:'remark'},
					        {name:'tryePrice'},
					        {name:'tryeBuyCode'},
					        {name:'tryeVacual'},
					        {name:'valid'},
					        {name:'minMiles'}
					        ],
    				root:'root',
    				totalProperty:'totalSize',
    				listeners:{'beforeload':function(store){
								store.baseParams["limit"]=pagingBar1.pageSize;
								}
							}
				})
				

					
						
				var pagingBar1 = new Ext.PagingToolbar({
												displayInfo:true,
												displayMsg:"${app:i18n_def('vmsoperation.page_prompt_current_data','当前显示')} {0} - {1} ${app:i18n_def('vmsoperation.page_prompt_total_data','总记录数目')} {2}",
												store:centerStore,
												pageSize:10,
												emptyMsg:"${app:i18n_def('vmsoperation.page_prompt_no_data','未检索到数据')}"});
				
				
				
				var centerPanel = new Ext.grid.GridPanel({
		            split: true,
		            width: 200,
		           // margins: '0 0 0 5',
		           	tbar:pagingBar1,
					region:'center',
					store:centerStore,
					sm: CK,
					cm: new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),CK,
					                              	{header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.6','轮胎品牌')}',dataIndex:"tryeBreed",sortable:false},
						                          	{header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.7','轮胎型号')}',dataIndex:"tryeModel",sortable:false},
						                          	{header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.8','轮胎编码')}',dataIndex:"tryeNo",sortable:false},
						                          	{header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.9','入库时间')}',dataIndex:"tryeInTm",sortable:false,renderer:datetimeRenderer,id:"dispalyDateFormat"},
						                          	{header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.10','轮胎性质')}',dataIndex:"tryeKind",sortable:false,renderer:comboboxRenderer,id:"tryeKind"},
						                          	{header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.11','已用里程(km)')}',dataIndex:"usedMile",sortable:false},
						                          	{header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.12','验收人')}',dataIndex:"checkEmp",sortable:false},
						                          	
						                          	{hidden:true,header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.13','备注')}',dataIndex:"remark",sortable:false},
						                          	{hidden:true,header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.14','库存id')}',dataIndex:"id",sortable:false},
						                          	{hidden:true,header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.15','轮胎价格')}',dataIndex:"tryePrice",sortable:false},
						                          	{hidden:true,header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.32','统购编码')}',dataIndex:"tryeBuyCode",sortable:false},
						                          	{hidden:true,header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.33','是否真空')}',dataIndex:"tryeVacual",sortable:false},
						                          	{hidden:true,header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.34','是否有效')}',dataIndex:"valid",sortable:false},
						                          	{hidden:true,header:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.34','最低标准里程')}',dataIndex:"minMiles",sortable:false}
						                          	
					                              ])
			});
			
			var queryForm2 = new Ext.form.FormPanel({
											frame:true,
											region:"north",
											autoHeight:true,
											items: [

												new Ext.form.FieldSet({
													labelWidth:80,
													height:40+23,
													layout:"column",
													title:"${app:i18n_def('vmsoperation.tryeData.page.1','查询条件')}",
													layout:'column',
													items:[
													       {	
													    	   xtype:"panel",
													    	   columnWidth:.5,
													    	   labelWidth:70,
													    	   labelAlign:"right",
													    	   layout:"form",
													    	   items:{
													    		   xtype:"combo",
													    		   id:"tryeBreed2",
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
													    			   select : function(){
																			Ext.getCmp("tryeModel2").setValue('');
																			Ext.getCmp("tryeModel2").store.removeAll();
																			Ext.getCmp("tryeModel2").store.load();
																			
																		}
													    	   }
													       }
													       },
													       {
													    	   xtype:"panel",
													    	   columnWidth:.5,
													    	   labelWidth:80,
													    	   labelAlign:"right",
													    	   layout:"form",
													    	   items:{
													    		 	xtype:"combo",
													    		   	id:"tryeModel2",
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
																				s.baseParams["tryeData.tryeBreed"] = Ext.getCmp('tryeBreed2').getValue();
																		  	},
																		  	load:function(t){
													    						  var all = new Ext.data.Record([]);
													    						  all.set('key', '');
													    		                  all.set('text', '${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}');
													    		                  t.insert(0, [all]);
													    					}
																      }
																	})
													    		 
													    	   		}
													       }
													       
													       
													       ]
													})
										]});
										
				tryeDlg = new Ext.Window({
					title:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.tryeStore','选择轮胎库存')}',
					width:650,
					height:450,
					frame:true,
					modal:true,
					//closeAction:'hide',
					layout:'fit',
					tbar: [{text:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.18','查询')}',minWidth:60,cls:'x-btn-normal',handler:function(){
								centerPanel.getStore().baseParams["tryeStore.tryeBreed"] = queryForm2.getForm().findField("tryeData.tryeBreed").getValue() ;
								centerPanel.getStore().baseParams["tryeStore.tryeModel"] = queryForm2.getForm().findField("tryeData.tryeModel").getValue() ;
								centerPanel.getStore().load();
								
							 }}
								,{text:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.19','选择')}',minWidth:60,cls:'x-btn-normal',handler:function(){
									var record = centerPanel.getSelectionModel().getSelected();
									Ext.getCmp('tryestorre_id').setValue(record.get("tryeBreed")+','+record.get("tryeModel")) ;
									vehicleTyre_tyrePrice.setValue(record.get("tryePrice")) ;
									vehicleTyre_tyreNo.setValue(record.get("tryeNo")) ;
									vehicleTyre_tryeInTm.setValue(record.get("tryeInTm")) ;
									vehicleTyre_brandModel.setValue(record.get("tryeBreed")) ;
									vehicleTyre_TryeStoreId.setValue(record.get("id")) ;
									vehicleTyre_tyreTypeNo.setValue(record.get("tryeModel")) ;  
									tryeDlg.close();
								}},{text:'${app:i18n_def('vmsoperation.vehicleTyre.page.23','取消')}',minWidth:60,cls:'x-btn-normal',handler:function(){tryeDlg.close();}} 
					       ], // end tbar
					items: [{
						        layout: 'border',
						        items: [queryForm2,
										centerPanel
						                ]
							}]  
					}) 
			} ;// end if
			tryeDlg.show(_curObj);
		} ; 
		
		Ext.override(Ext.form.DateField, {
			initComponent : function(){
		        Ext.form.DateField.superclass.initComponent.call(this);
		        if(typeof this.minValue == "string"){
		            this.minValue = this.parseDate(this.minValue);
		        }
		        if(typeof this.maxValue == "string"){
		            this.maxValue = this.parseDate(this.maxValue);
		        }
		        this.ddMatch = null;
		        if(this.disabledDates){
		            var dd = this.disabledDates;
		            var re = "(?:";
		            for(var i = 0; i < dd.length; i++){
		                re += dd[i];
		                if(i != dd.length-1) re += "|";
		            }
		            this.ddMatch = new RegExp(re + ")");
		        }
		        
		        this.addEvents('selectdt', true);
		    },
			onTriggerClick : function(){
		        if(this.disabled){
		            return;
		        }
		        if(this.menu == null){
		            this.menu = new Ext.menu.DateMenu();
		            this.menu.picker.on('select', function(picker, dt){
			        	this.fireEvent('selectdt', dt);
			        }, this);
		        }
		        
		        Ext.apply(this.menu.picker,  {
		            minDate : this.minValue,
		            maxDate : this.maxValue,
		            disabledDatesRE : this.ddMatch,
		            disabledDatesText : this.disabledDatesText,
		            disabledDays : this.disabledDays,
		            disabledDaysText : this.disabledDaysText,
		            format : this.format,
		            minText : String.format(this.minText, this.formatDate(this.minValue)),
		            maxText : String.format(this.maxText, this.formatDate(this.maxValue))
		        });
		        this.menu.on(Ext.apply({}, this.menuListeners, {
		            scope:this
		        }));
		        this.menu.picker.setValue(this.getValue() || new Date());
		        this.menu.show(this.el, "tl-bl?");
		    }
		});
	</ext:script>
	
	
	<ext:viewport layout="border">
		<ext:items>
			<ext:treePanel region="west" autoScroll="true" width="210">
				<ext:treeLoader url="../vmscommon/deptTree.action" idField="id" var="leftTree" textField="deptName" handler="onSelectNode"/>
				<ext:asyncTreeNode text="${app:i18n_def('vmsoperation.vehicleTyre.page.1','顺丰')}" id="0"/>
			</ext:treePanel>
			<ext:panel region="center" layout="border" frame="true">
				<ext:tbar>
					<ext:button text="${app:i18n_menu_def('vehicleTyre.button.search.name', app:i18n('search'))}" var="btnSearch" handler="onSearch"/>
					<app:isPermission code="/vmsoperation/saveVehicleTyre_1.action"><ext:button text="${app:i18n_menu_def('vehicleTyre.button.add.name', app:i18n('vehicleTyre.add'))}" var="btnAdd" handler="onAdd"/></app:isPermission>
					<app:isPermission code="/vmsoperation/exportTearTyre_1.action"><ext:button text="${app:i18n_def('vmsoperation.vehicleTyre.page.2','导出')}" var="btnExport" handler="onExport"/></app:isPermission>
				</ext:tbar>
				<ext:items>
					<ext:formPanel var="queryView" region="north" autoHeight="true" frame="true">
						<ext:items>
							<ext:fieldSet layout="column" title="${app:i18n('queryCondition')}" autoHeight="true">
								<ext:items>
									<ext:hidden name="query.tyreId" var="query_tyreId"/>
									<ext:hidden name="query.deptId" var="query_deptId"/>
									<ext:panel width="250" layout="form">
										<ext:items>
									<ext:textField name="query.vehicleCode" var="query_vehicleCode" fieldLabel="${app:i18n_def('vmsoperation.VehicleCert.vehicleCode','车牌')}" width="125"/>
										</ext:items>
									</ext:panel>
									<ext:panel width="250" layout="form">
										<ext:items>
											<ext:textField name="query.tyreNo" var="query_tyreNo" fieldLabel="${app:i18n('vehicleTyre.tyreNo')}" width="125"/>
										</ext:items>
									</ext:panel>
									<ext:panel width="250" layout="form">
										<ext:items>
											<ext:dateField name="query.installStartDt" var="query_installStartDt" fieldLabel="${app:i18n('vehicleTyre.installStartDt')}" width="125" format="Y-m-d"/>
										</ext:items>
									</ext:panel>
									<ext:panel width="250" layout="form">
										<ext:items>
											<ext:dateField name="query.installEndDt" var="query_installEndDt" fieldLabel="${app:i18n('vehicleTyre.installEndDt')}" width="125" format="Y-m-d"/>
										</ext:items>
									</ext:panel>
									<ext:panel width="250" layout="form">
										<ext:items>
											<ext:comboBox name="query.vehicleState" var="query_vehicleState" fieldLabel="${app:i18n_def('accident.vehicleState','车辆状态')}" width="125" hiddenName="query.vehicleState" valueField="key" displayField="value" triggerAction="all" editable="false" mode="local" data="['1','${app:i18n_def('vmsoperation.vehicleTyre.page.101','正常')}'],['2','${app:i18n_def('vmsoperation.vehicleTyre.page.102','转卖')}'],['3','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.20','停用')}'],['4','${app:i18n_def('vmsoperation.vehicleTyre.page.3','报废')}']" listeners="{expand:initComboBox}"/>
										</ext:items>
									</ext:panel>
									<ext:panel width="250" layout="form">
										<ext:items>
											<ext:textField name="query.wheelBase" var="query_wheelBase" fieldLabel="${app:i18n_def('vmsoperation.vehicleTyre.page.8','品牌轴距')}" width="125"/>
										</ext:items>
									</ext:panel>


									<ext:panel width="250" layout="form">
										<ext:items>
											new Ext.form.ComboBox({
												id:"query_brandModel",
												hiddenName:"query.brandModel",
												fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.2','轮胎品牌')}',
												typeAhead: true,
												mode: 'remote',
												width:125,
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
													'select':function(obj){
														if(obj.value == '${app:i18n_def('vmsoperation.tryeData.page.26','其他')}'){
															btnSave.disable() ;
														}else{
															btnSave.enable() ;
														}
														
													}
													
												}
						
											})
										</ext:items>
									</ext:panel>
									<ext:panel width="250" layout="form">
										<ext:items>
											<ext:comboBox name="query.position" var="query_position" fieldLabel="${app:i18n_def('vehicleTyre.vehicle.position','装用位置')}" width="125" hiddenName="query.position" valueField="key" displayField="value" triggerAction="all" editable="false" mode="local" data="['14','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.21','在用胎')}'],['15','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.22','备用胎')}']" listeners="{expand:initComboBox}"/>
										</ext:items>
									</ext:panel>
									
									
									<ext:panel width="250" layout="form">
										<ext:items>
											<ext:dateField name="query.teardownStartDt" var="query_teardownStartDt" fieldLabel="${app:i18n('vehicleTyre.teardownStartDt')}" width="125" format="Y-m-d"/>
										</ext:items>
									</ext:panel>
									<ext:panel width="250" layout="form">
										<ext:items>
											<ext:dateField name="query.teardownEndDt" var="query_teardownEndDt" fieldLabel="${app:i18n('vehicleTyre.teardownEndDt')}" width="125" format="Y-m-d"/>
										</ext:items>
									</ext:panel>
									<ext:panel width="250" layout="form">
										<ext:items>
											<ext:numberField name="query.useDays" var="query_useDays" fieldLabel="${app:i18n('vehicleTyre.useDays')}" width="125" decimalPrecision="0"/>
										</ext:items>
									</ext:panel>
									<ext:panel width="250" layout="form">
										<ext:items>
											<ext:comboBox name="query.state" var="query_state" fieldLabel="${app:i18n('vehicleTyre.state')}" width="125" hiddenName="query.state" valueField="key" displayField="value" triggerAction="all" editable="false" mode="local" data="['0','${app:i18n_def('vmsoperation.vehicleTyre.page.3','报废')}'],['1','${app:i18n_def('vmsoperation.vehicleTyre.page.4','正在使用')}'],['3','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.23','待用')}'],['2','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.5','历史')}']" listeners="{expand:initComboBox}"/>
										</ext:items>
									</ext:panel>
									<ext:panel width="250" layout="form" listeners="{afterlayout:onUIShow}">
										<ext:items>
											<ext:numberField name="query.runMiles" var="query_runMiles" fieldLabel="${app:i18n('vehicleTyre.runMiles')}" width="125" decimalPrecision="0" minValue="1" maxValue="1000000"/>
										</ext:items>
									</ext:panel>
								</ext:items>
							</ext:fieldSet>
						</ext:items>
					</ext:formPanel>

					<ext:gridPanel var="gridView" region="center" frame="true" autoExpandColumn="grid_modifiedTm">
						<ext:store var="gridStore" url="findPageByVehicleTyre.action" remoteSort="true">
							<ext:jsonReader totalProperty="totalSize" root="vehicleTyres">
								<ext:fields type="com.sf.module.vmsoperation.domain.VehicleTyre"/>
							</ext:jsonReader>
						</ext:store>
						<ext:pagingToolbar var="pagingBar" pageSize="20" store="gridStore" 
						displayMsg="${app:i18n_def('vmsoperation.page_prompt_current_data','当前显示')} {0} - {1} ${app:i18n_def('vmsoperation.page_prompt_total_data','总记录数目')} {2}"
						emptyMsg="${app:i18n_def('vmsoperation.page_prompt_no_data','未检索到数据')}"
						displayInfo="true"/>
						<ext:columnModel>
							<ext:propertyColumnModel dataIndex="tyreId" id="grid_tyreId" header="${app:i18n('vehicleTyre.tyreId')}" hidden="true"/>
							<ext:propertyColumnModel dataIndex="vehicle.id" id="grid_vehicleId" header="${app:i18n('vehicleTyre.vehicleId')}" hidden="true"/>
							<ext:propertyColumnModel dataIndex="vehicle.vehicleCode" id="grid_vehicleCode" header="${app:i18n('vehicleTyre.vehicleId')}" hidden="true"/>
							<ext:propertyColumnModel dataIndex="deptId" id="grid_deptId" header="${app:i18n('vehicleTyre.deptId')}" hidden="true"/>
							<ext:propertyColumnModel dataIndex="hDeptName" id="grid_hDeptName" header="${app:i18n_def('vmsoperation.vehicleTyre.page.5','经营本部')}" />
							<ext:propertyColumnModel dataIndex="deptName" id="grid_DeptName" header="${app:i18n_def('vmsoperation.vehicleTyre.page.6','所在单位')}" />
							<ext:propertyColumnModel dataIndex="vehicle" id="grid_vehicleCode" header="${app:i18n_def('vmsoperation.vehicleTyre.page.7','车牌')}" renderer="showVehicleCode"/>
							<ext:propertyColumnModel dataIndex="vehicle" id="grid_ModelWheel" header="${app:i18n_def('vmsoperation.vehicleTyre.page.8','品牌轴距')}" renderer="showModelWheel"/>
							<ext:propertyColumnModel dataIndex="tyreNo" id="grid_tyreNo" header="${app:i18n('vehicleTyre.tyreNo')}"/>
							<ext:propertyColumnModel dataIndex="installDt" id="grid_installDt" header="${app:i18n('vehicleTyre.installDt')}" renderer="datetimeRenderer">
								<ext:dateField var="grid_installDt" format="Y-m-d"/>
							</ext:propertyColumnModel>
							<ext:propertyColumnModel dataIndex="tyrePrice" id="grid_tyrePrice" header="${app:i18n('vehicleTyre.tyrePrice')}"/>
							<ext:propertyColumnModel dataIndex="installMiles" id="grid_installMiles" header="${app:i18n('vehicleTyre.installMiles')}"/>
							<ext:propertyColumnModel dataIndex="brandModel" id="grid_brandModel" header="${app:i18n('vehicleTyre.brandModel')}"/>
							<ext:propertyColumnModel dataIndex="position" id="grid_position" header="${app:i18n('vehicleTyre.position')}" renderer="comboboxRenderer">
								<ext:comboBox var="grid_position" valueField="key" displayField="value" allowBlank="false" mode="local" data="['14','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.21','在用胎')}'],['15','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.22','备用胎')}']"/>
							</ext:propertyColumnModel>
							
							<ext:propertyColumnModel dataIndex="tryeModel" id="grid_tryeModel" header="${app:i18n('vmsoperation.vehicleTyre.page.22')}"/>
							
							<ext:propertyColumnModel dataIndex="installRemark" id="grid_installRemark" header="${app:i18n('vehicleTyre.installRemark')}"/>
							<ext:propertyColumnModel dataIndex="teardownDt" id="grid_teardownDt" header="${app:i18n('vehicleTyre.teardownDt')}" renderer="datetimeRenderer">
								<ext:dateField var="grid_teardownDt" format="Y-m-d"/>
							</ext:propertyColumnModel>
							<ext:propertyColumnModel dataIndex="teardownPrice" id="grid_teardownPrice" header="${app:i18n('vehicleTyre.teardownPrice')}"/>
							<ext:propertyColumnModel dataIndex="teardownMiles" id="grid_teardownMiles" header="${app:i18n('vehicleTyre.teardownMiles')}"/>
							<ext:propertyColumnModel dataIndex="specialMatters" id="grid_specialMatters" header="${app:i18n('vehicleTyre.specialMatters')}"/>
							<ext:propertyColumnModel dataIndex="teardownRemark" id="grid_teardownRemark" header="${app:i18n('vehicleTyre.teardownRemark')}" renderer="teardownRemarkRenderer"/>
							<ext:propertyColumnModel dataIndex="useDays" id="grid_useDays" header="${app:i18n('vehicleTyre.useDays')}"/>
							<ext:propertyColumnModel dataIndex="state" id="grid_state" header="${app:i18n('vehicleTyre.state')}" renderer="stateRenderer" />
							<ext:propertyColumnModel dataIndex="baseMiles" id="grid_baseMiles" header="${app:i18n('vehicleTyre.baseMiles')}"/>
							<ext:propertyColumnModel dataIndex="totalMiles" id="grid_totalMiles" header="${app:i18n('vehicleTyre.totalMiles')}"/>
							<ext:propertyColumnModel dataIndex="runMiles" id="grid_runMiles" header="${app:i18n('vehicleTyre.runMiles')}"/>
							<ext:propertyColumnModel dataIndex="remark" id="grid_remark" header="${app:i18n('vehicleTyre.remark')}"/>
							<ext:propertyColumnModel dataIndex="createdEmpCode" id="grid_createdEmpCode" header="${app:i18n('vehicleTyre.createdEmpCode')}" hidden="true"/>
							<ext:propertyColumnModel dataIndex="createdTm" id="grid_createdTm" header="${app:i18n('vehicleTyre.createdTm')}" renderer="datetimeRenderer" hidden="true">
								<ext:dateField var="grid_createdTm" format="Y-m-d"/>
							</ext:propertyColumnModel>
							<ext:propertyColumnModel dataIndex="modifiedEmpCode" id="grid_modifiedEmpCode" header="${app:i18n('vehicleTyre.modifiedEmpCode')}" hidden="true"/>
							<ext:propertyColumnModel dataIndex="modifiedTm" id="grid_modifiedTm" header="${app:i18n('vehicleTyre.modifiedTm')}" renderer="datetimeRenderer" hidden="true">
								<ext:dateField var="grid_modifiedTm" format="Y-m-d"/>
							</ext:propertyColumnModel>
							
							
						</ext:columnModel>
					</ext:gridPanel>
				</ext:items>
			</ext:panel>
		</ext:items>
	</ext:viewport>

	<ext:window var="editView" closeAction="hide" width="800" height="400" layout="border" modal="true" frame="true">
		<ext:tbar>
			<ext:button text="${app:i18n_menu_def('vehicleTyre.button.save.name12345', app:i18n('save12345'))}" var="btnSave" handler="onSave"/>
			<ext:button text="${app:i18n_menu_def('vehicleTyre.button.edit.name', app:i18n('edit'))}" var="btnEdit" handler="onEdit"/>
			<ext:button text="${app:i18n_menu_def('vehicleTyre.button.delete.name', app:i18n('delete'))}" var="btnDelete" handler="onDelete"/>
			<ext:button text="${app:i18n_menu_def('vehicleTyre.button.add.name', app:i18n('vehicleTyre.tear'))}" var="btnTear" handler="onTear"/>
			<ext:button text="${app:i18n_def('vmsoperation.vehicleTyre.page.23','取消')}" var="btnCancel" handler="function(){editView.hide();}"/>
		</ext:tbar>
		<ext:items>
			<ext:formPanel var="editForm" region="north" layout="column" frame="true" autoHeight="true" labelWidth="120">
				<ext:submitAction name="saveVehicleTyre" url="saveVehicleTyre.action" success="showSaveSuccessInfo" failure="showSaveFailureInfo" waitMsg="${app:i18n('saving')}"/>
				<ext:items>
					<ext:panel layout="form" width="280">
						<ext:items>
							<ext:hidden name="vehicleTyre.tyreId" var="vehicleTyre_tyreId"/>
							<ext:hidden name="vehicleTyre.createdEmpCode" var="vehicleTyre_createdEmpCode"/>
							<ext:hidden name="vehicleTyre.createdTm" var="vehicleTyre_createdTm"/>
							<ext:hidden name="vehicleTyre.modifiedEmpCode" var="vehicleTyre_modifiedEmpCode"/>
							<ext:hidden name="vehicleTyre.modifiedTm" var="vehicleTyre_modifiedTm"/>
							<ext:textField name="vehicleTyre.tyreTypeNo" var="vehicleTyre_tyreTypeNo" fieldLabel="${app:i18n('vehicleTyre.tyreTypeId')}" width="125" readOnly="true"/>
							<ext:hidden name="vehicleTyre.tyreTypeId" var="vehicleTyre_tyreTypeId" />
							<ext:hidden name="vehicleTyre.deptId" var="vehicleTyre_adddeptId"/>
							<ext:hidden name="vehicleTyre.tryeStoreId" var="vehicleTyre_TryeStoreId"/>
							
							
							,new Ext.form.ComboBox({
									id:'vehicleCbx',
									fieldLabel:'${app:i18n_def('vmsoperation.VehicleCert.vehicleCode','车牌')}<font color=red>*</font>',
									valueField:'id',
									displayField:'vehicleCode',
									triggerAction:'all',
									minChars:2,
									width:125, 
									queryParam:'vehicleCode',
									forceSelection:true,
									editable:true,
									mode:'remote',
									store:new Ext.data.JsonStore({
										root:'svs',
										url:'../vmsbase/listVehicleForComboBox.action',
										fields:['id', 'vehicleCode', 'factoryDt','tyreType']
									}),
									listeners: {  
										'beforequery': function(qe){
											//每次都查询
											delete qe.combo.lastQuery;  
										},
										'select':function(t, r){
										
											vehicleTyre_vehicleId.setValue(t.getValue());
											editvehicleFactoryDate.setValue(r.data.factoryDt.substr(0,10));
											
											vehicleTyre_tyrePrice.setValue(null);
											vehicleTyre_brandModel.setValue(null);
											vehicleTyre_installRemark.setValue(null);
											//editvehicleFactoryDate.setValue(null);
											vehicleTyre_tyreNo.setValue(null);
											vehicleTyre_installDt.setValue(null);
											vehicleTyre_installMiles.setValue(null);
											onAddSearch();

										}
									},
									tabIndex:"1",
									allowBlank:false,
									blankText:"${app:i18n_def('vmsoperation.VehicleCert.vehicleCodeisNullmsg','车牌不能为空')}"
								})

							<ext:hidden name="vehicleTyre.vehicle.id" var="vehicleTyre_vehicleId"/>
							<ext:numberField name="vehicleTyre.tyrePrice" var="vehicleTyre_tyrePrice" readOnly="true" tabIndex="5" fieldLabel="${app:i18n('vehicleTyre.tyrePrice')}" width="125" decimalPrecision="2" allowBlank="false" blankText="${app:i18n_def('vmsoperation.vehicleTyre.page.26','请填写装用价格')}" maxValue="999999999" maxText="${app:i18n_def('vmsoperation.vehicleTyre.page.max9999999999','装用价格不能大于999999999')}" minValue="-999999999" minText="${app:i18n_def('vmsoperation.vehicleTyre.page.min9999999999','装用价格不能小于-999999999')}"/>
							<ext:textField name="vehicleTyre.brandModel" var="vehicleTyre_brandModel" readOnly="true" tabIndex="7" fieldLabel="${app:i18n('vehicleTyre.brandModel')}" width="125"/>
							,new Ext.form.TriggerField({
									id:'tryestorre_id',
									readOnly:true,
									triggerAction:'all',
									allowBlank:false,
									minChars:2,
									width:125, 
									editable:false,
									fieldLabel:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.24','轮胎库存')}',
									triggerClass : 'x-form-search-trigger',
									onTriggerClick :function(obj){
									    	myTriggerClick(obj) ;
									    }
							})
							<ext:textField name="vehicleTyre.installRemark" var="vehicleTyre_installRemark" tabIndex="10" fieldLabel="${app:i18n('vehicleTyre.installRemark')}" width="125"/>
						</ext:items>
					</ext:panel>
					<ext:panel layout="form" width="280">
						<ext:items>
						
						
							<ext:textField  name="vehicleFactoryDate" var="editvehicleFactoryDate" fieldLabel="${app:i18n('vehicleTyre.vehiclefactorydate')}" width="125" readOnly="true"/>
							<ext:textField name="vehicleTyre.tyreNo" var="vehicleTyre_tyreNo" readOnly="true" tabIndex="3" fieldLabel="${app:i18n('vehicleTyre.tyreNo')}" width="125" allowBlank="false" blankText="${app:i18n_def('vmsoperation.vehicleTyre.page.27','请填写轮胎编号')}" />
							<ext:dateField name="vehicleTyre.installDt" var="vehicleTyre_installDt" tabIndex="4" fieldLabel="${app:i18n('vehicleTyre.installDt')}" readOnly="true" listeners="{'selectdt':setInstallMiles}" width="125" format="Y-m-d" allowBlank="false" blankText="${app:i18n_def('vmsoperation.vehicleTyre.page.28','请填写装用日期')}"/>
							<ext:numberField name="vehicleTyre.installMiles" var="vehicleTyre_installMiles" tabIndex="6" fieldLabel="${app:i18n('vehicleTyre.installMiles')}" width="125" decimalPrecision="0" readOnly="true" allowBlank="false" blankText="${app:i18n_def('vmsoperation.vehicleTyre.page.29','请填写装用里程')}" maxValue="999999999" maxText="${app:i18n_def('vmsoperation.vehicleTyre.page.max99999999991','装用里程不能大于999999999')}" minValue="-999999999" minText="${app:i18n_def('vmsoperation.vehicleTyre.page.min99999999991','装用里程不能小于-999999999')}"/>
							<ext:comboBox name="vehicleTyre.position" var="vehicleTyre_position" tabIndex="8" fieldLabel="${app:i18n('vehicleTyre.position')}" allowBlank="false" width="125" hiddenName="vehicleTyre.position" valueField="key" displayField="value" triggerAction="all"   editable="false" mode="local" data="['14','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.21','在用胎')}'],['15','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.22','备用胎')}']" />

							<ext:hidden  var="vehicleTyre_tryeInTm"/>
							
						</ext:items>
					</ext:panel>
				</ext:items>
			</ext:formPanel>
				<ext:gridPanel var="usegridView" region="center" frame="true" autoExpandColumn="grid_modifiedTm" listeners="{rowdblclick:onEdit}">
				<ext:store var="usegridStore" url="findPageByVehicleId.action" remoteSort="true">
					<ext:jsonReader totalProperty="totalSize" root="vehicleTyres">
						<ext:fields type="com.sf.module.vmsoperation.domain.VehicleTyre"/>
					</ext:jsonReader>
				</ext:store>
				<ext:pagingToolbar var="usepagingBar" pageSize="20" store="usegridStore" 
				displayMsg="${app:i18n_def('vmsoperation.page_prompt_current_data','当前显示')} {0} - {1} ${app:i18n_def('vmsoperation.page_prompt_total_data','总记录数目')} {2}"
						emptyMsg="${app:i18n_def('vmsoperation.page_prompt_no_data','未检索到数据')}"
						displayInfo="true"/>
				<ext:checkboxSelectionModel/>
				<ext:columnModel>
					<ext:propertyColumnModel dataIndex="tyreId" id="grid_tyreId" header="${app:i18n('vehicleTyre.tyreId')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="vehicle.id" id="grid_vehicleId" header="${app:i18n('vehicleTyre.vehicleId')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="vehicle.vehicleCode" id="grid_vehicleCode" header="${app:i18n('vehicleTyre.vehicleId')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="deptId" id="grid_deptId" header="${app:i18n('vehicleTyre.deptId')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="tyreNo" id="grid_tyreNo" header="${app:i18n('vehicleTyre.tyreNo')}"/>
					<ext:propertyColumnModel dataIndex="tryeModel" id="grid_tryeModel" header="${app:i18n('vmsoperation.vehicleTyre.page.22')}"/>
							
					<ext:propertyColumnModel dataIndex="tyrePrice" id="grid_tyrePrice" header="${app:i18n('vehicleTyre.tyrePrice')}"/>
					<ext:propertyColumnModel dataIndex="brandModel" id="grid_brandModel" header="${app:i18n('vehicleTyre.brandModel')}"/>
					<ext:propertyColumnModel dataIndex="installMiles" id="grid_installMiles" header="${app:i18n('vehicleTyre.installMiles')}"/>
					<ext:propertyColumnModel dataIndex="installDt" id="grid_installDt" header="${app:i18n('vehicleTyre.installDt')}" renderer="datetimeRenderer">
						<ext:dateField var="grid_installDt" format="Y-m-d"/>
					</ext:propertyColumnModel>
					<ext:propertyColumnModel dataIndex="position" id="grid_position" header="${app:i18n('vehicleTyre.position')}" renderer="comboboxRenderer">
						<ext:comboBox var="grid_position" valueField="key" displayField="value" allowBlank="false" mode="local" data="['14','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.21','在用胎')}'],['15','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.22','备用胎')}']"/>
					</ext:propertyColumnModel>
					<ext:propertyColumnModel dataIndex="installRemark" id="grid_installRemark" header="${app:i18n('vehicleTyre.installRemark')}"/>
					<ext:propertyColumnModel dataIndex="teardownDt" id="grid_teardownDt" header="${app:i18n('vehicleTyre.teardownDt')}" renderer="datetimeRenderer" hidden="true">
						<ext:dateField var="grid_teardownDt" format="Y-m-d"/>
					</ext:propertyColumnModel>
					<ext:propertyColumnModel dataIndex="teardownPrice" id="grid_teardownPrice" header="${app:i18n('vehicleTyre.teardownPrice')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="teardownMiles" id="grid_teardownMiles" header="${app:i18n('vehicleTyre.teardownMiles')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="specialMatters" id="grid_specialMatters" header="${app:i18n('vehicleTyre.specialMatters')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="teardownRemark" id="grid_teardownRemark" header="${app:i18n('vehicleTyre.teardownRemark')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="useDays" id="grid_useDays" header="${app:i18n('vehicleTyre.useDays')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="state" id="grid_state" header="${app:i18n('vehicleTyre.state')}" renderer="stateRenderer" hidden="true" />
					<ext:propertyColumnModel dataIndex="baseMiles" id="grid_baseMiles" header="${app:i18n('vehicleTyre.baseMiles')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="totalMiles" id="grid_totalMiles" header="${app:i18n('vehicleTyre.totalMiles')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="runMiles" id="grid_runMiles" header="${app:i18n('vehicleTyre.runMiles')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="remark" id="grid_remark" header="${app:i18n('vehicleTyre.remark')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="createdEmpCode" id="grid_createdEmpCode" header="${app:i18n('vehicleTyre.createdEmpCode')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="createdTm" id="grid_createdTm" header="${app:i18n('vehicleTyre.createdTm')}" renderer="datetimeRenderer" hidden="true">
						<ext:dateField var="grid_createdTm" format="Y-m-d"/>
					</ext:propertyColumnModel>
					<ext:propertyColumnModel dataIndex="modifiedEmpCode" id="grid_modifiedEmpCode" header="${app:i18n('vehicleTyre.modifiedEmpCode')}" hidden="true"/>
					<ext:propertyColumnModel dataIndex="modifiedTm" id="grid_modifiedTm" header="${app:i18n('vehicleTyre.modifiedTm')}" renderer="datetimeRenderer" hidden="true">
						<ext:dateField var="grid_modifiedTm" format="Y-m-d"/>
					</ext:propertyColumnModel>
					<ext:propertyColumnModel dataIndex="version" id="grid_version" header="${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.25','版本')}" hidden="true"/>
					
				</ext:columnModel>
			</ext:gridPanel>
		</ext:items>
	</ext:window>

			<ext:window var="modifyView" closeAction="hide" width="600" height="300" layout="border" modal="true" frame="true" title="${app:i18n_def('vmsoperation.vehicleTyre.page.30','在用轮胎资料修改')}">
		<ext:tbar>
			<ext:button text="${app:i18n_menu_def('vehicleTyre.button.save.name', app:i18n('save'))}" var="btnModifySave" handler="onModifySave"/>
		</ext:tbar>
		<ext:items>
			<ext:formPanel var="modifyForm" region="center" layout="column" frame="true" labelWidth="120">
				<ext:submitAction name="modifyVehicleTyre" url="saveVehicleTyre.action" success="showSaveEditSuccessInfo" failure="showSaveFailureInfo" waitMsg="${app:i18n('saving')}"/>
				<ext:items>
					<ext:panel layout="form" width="280">
						<ext:items>
							<ext:hidden name="vehicleTyre.tyreId" var="modifyvehicleTyre_tyreId"/>
							<ext:hidden name="vehicleTyre.createdEmpCode" var="modifyvehicleTyre_createdEmpCode"/>
							<ext:hidden name="vehicleTyre.createdTm" var="modifyvehicleTyre_createdTm"/>
							<ext:hidden name="vehicleTyre.modifiedEmpCode" var="modifyvehicleTyre_modifiedEmpCode"/>
							<ext:hidden name="vehicleTyre.modifiedTm" var="modifyehicleTyre_modifiedTm"/>
							<ext:textField name="vehicleTyre.tyreTypeNo" var="modifyvehicleTyre_tyreTypeNo" fieldLabel="${app:i18n('vehicleTyre.tyreTypeId')}" width="125" readOnly="true"/>
							<ext:hidden name="vehicleTyre.tyreTypeId" var="modifyvehicleTyre_tyreTypeId" />
							<ext:hidden name="vehicleTyre.vehicle.id" var="modifyvehicleTyre_vehicleId"/>
							<ext:textField name="modifyvehicle" var="modifyvehicle" fieldLabel="${app:i18n('vehicleTyre.vehicle')}"	width="125" readOnly="true"	/>
							<ext:numberField name="vehicleTyre.tyrePrice" var="modifyvehicleTyre_tyrePrice" tabIndex="5" fieldLabel="${app:i18n('vehicleTyre.tyrePrice')}" width="125" decimalPrecision="2" allowBlank="false" blankText="${app:i18n_def('vmsoperation.vehicleTyre.page.26','请填写装用价格')}" maxValue="999999999" maxText="${app:i18n_def('vmsoperation.vehicleTyre.page.max9999999999','装用价格不能大于999999999')}" minValue="-999999999" minText="${app:i18n_def('vmsoperation.vehicleTyre.page.min9999999999','装用价格不能小于-999999999')}"/>
							<ext:textField name="vehicleTyre.brandModel" var="modifyvehicleTyre_brandModel" tabIndex="7" fieldLabel="${app:i18n('vehicleTyre.brandModel')}" width="125"/>
							<ext:textField name="vehicleTyre.installRemark" var="modifyvehicleTyre_installRemark" tabIndex="10" fieldLabel="${app:i18n('vehicleTyre.installRemark')}" width="125"/>
						</ext:items>
					</ext:panel>
					<ext:panel layout="form" width="280">
						<ext:items>
							<ext:hidden name="vehicleTyre.deptId" var="modifyvehicleTyre_adddeptId"/>
							<ext:textField name="vehicleFactoryDate" var="modifyvehicleFactoryDate" fieldLabel="${app:i18n('vehicleTyre.vehiclefactorydate')}" width="125" readOnly="true"/>
							<ext:textField name="vehicleTyre.tyreNo" var="modifyvehicleTyre_tyreNo" tabIndex="3" fieldLabel="${app:i18n('vehicleTyre.tyreNo')}" width="125" readOnly="true"/>
							<ext:dateField name="vehicleTyre.installDt" var="modifyvehicleTyre_installDt" tabIndex="4" fieldLabel="${app:i18n('vehicleTyre.installDt')}" width="125" format="Y-m-d" allowBlank="false" blankText="${app:i18n_def('vmsoperation.vehicleTyre.page.28','请填写装用日期')}"/>
							<ext:numberField name="vehicleTyre.installMiles" var="modifyvehicleTyre_installMiles" tabIndex="6" fieldLabel="${app:i18n('vehicleTyre.installMiles')}" width="125" decimalPrecision="0" maxValue="999999999" maxText="${app:i18n_def('vmsoperation.vehicleTyre.page.max99999999991','装用里程不能大于999999999')}" minValue="-999999999" minText="${app:i18n_def('vmsoperation.vehicleTyre.page.min99999999991','装用里程不能小于-999999999')}" readOnly="true"/>
							<ext:comboBox name="vehicleTyre.position" var="modifyvehicleTyre_position" tabIndex="8" fieldLabel="${app:i18n('vehicleTyre.position')}" width="125" hiddenName="vehicleTyre.position" valueField="key" displayField="value" triggerAction="all" allowBlank="false" editable="false" mode="local" data="['14','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.21','在用胎')}'],['15','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.22','备用胎')}']" />
						</ext:items>
					</ext:panel>
				</ext:items>
			</ext:formPanel>
		</ext:items>
	</ext:window>

		<ext:window var="tearView" closeAction="hide" width="600" height="300" layout="border" modal="true" frame="true">
		<ext:tbar>
			<ext:button text="${app:i18n_menu_def('vehicleTyre.button.save.name', app:i18n('save'))}" var="btnTearSave" handler="onTearSave"/>
			<ext:button text="${app:i18n_menu_def('vehicleTyre.button.reset.name', app:i18n('reset'))}" var="btnTearReset" handler="tearReset"/>
		</ext:tbar>
		<ext:items>
			<ext:formPanel var="tearForm" region="center" layout="column" frame="true" labelWidth="120">
				<ext:submitAction name="tearVehicleTyre" url="tearVehicleTyre.action" success="showSaveTearSuccessInfo" failure="showSaveFailureInfo" waitMsg="${app:i18n('saving')}"/>
				<ext:items>
					<ext:panel layout="form" width="280">
						<ext:items>
							<ext:hidden name="vehicleTyre.tyreId" var="vehicleTyre_teartyreId" />
							<ext:hidden name="vehicleTyre.createdEmpCode" var="vehicleTyre_createdEmpCode"/>
							<ext:hidden name="vehicleTyre.createdTm" var="vehicleTyre_createdTm"/>
							<ext:hidden name="vehicleTyre.modifiedEmpCode" var="vehicleTyre_modifiedEmpCode"/>
							<ext:hidden name="vehicleTyre.modifiedTm" var="vehicleTyre_modifiedTm"/>
							
							<ext:textField name="tearvehicle" var="tearvehicle" fieldLabel="${app:i18n('vehicleTyre.vehicle')}"	width="125" readOnly="true"	/>
							<ext:hidden name="vehicleTyre.vehicle.id" var="vehicleTyre_tearvehicleId"/>
							
							<ext:comboBox name="vehicleTyre.position"
							var="vehicleTyre_position" tabIndex="8" fieldLabel="${app:i18n('vehicleTyre.position')}" width="125" hiddenName="vehicleTyre.position"  valueField="key" displayField="value" triggerAction="all" allowBlank="false" editable="false" mode="local" data="['14','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.21','在用胎')}'],['15','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.22','备用胎')}']" />
							
							<ext:numberField name="vehicleTyre.teardownMiles" var="vehicleTyre_teardownMiles" readOnly="true"  tabIndex="9" fieldLabel="${app:i18n('vehicleTyre.teardownMiles')}" width="125" decimalPrecision="0"  blankText="${app:i18n_def('vmsoperation.vehicleTyre.page.33','拆卸里程不能为空')}" maxValue="999999999" maxText="${app:i18n_def('vmsoperation.vehicleTyre.page.max99999999993','拆卸里程不能大于999999999')}" minValue="-999999999" minText="${app:i18n_def('vmsoperation.vehicleTyre.page.min99999999993','拆卸里程不能小于-999999999')}"/>
							
							<ext:comboBox name="vehicleTyre.teardownRemark"
							var="vehicleTyre_teardownRemark" tabIndex="10" fieldLabel="${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.26','拆卸备注')}" width="125" hiddenName="vehicleTyre.teardownRemark"  valueField="key" allowBlank="false" displayField="value" triggerAction="all"  editable="false" mode="local" data="['0','${app:i18n_def('vmsoperation.vehicleTyre.page.3','报废')}'],['3','${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.23','待用')}']" />
							
						</ext:items>
					</ext:panel>
					<ext:panel layout="form" width="280">
						<ext:items>
							<ext:hidden name="vehicleTyre.deptId" var="vehicleTyre_teardeptId"/>
							<ext:textField name="vehicleFactoryDate" var="vehicleFactoryDate" fieldLabel="${app:i18n('vehicleTyre.vehiclefactorydate')}" width="125" readOnly="true"/>
							<ext:dateField name="vehicleTyre.teardownDt" var="vehicleTyre_teardownDt" readOnly="true" tabIndex="11" fieldLabel="${app:i18n('vehicleTyre.teardownDt')}" width="125" format="Y-m-d" listeners="{'selectdt':setTeardownMiles}" allowBlank="false" blankText="${app:i18n_def('vmsoperation.vehicleTyre.page.32','拆卸日期不能为空')}"/>
							<ext:textField name="vehicleTyre.tyreNo" var="tearvehicleTyre_tyreNo"  tabIndex="3" fieldLabel="${app:i18n('vehicleTyre.tyreNo')}" width="125" readOnly="true"/>
							
						</ext:items>
					</ext:panel>
				</ext:items>
			</ext:formPanel>
		</ext:items>
	</ext:window>
	<ext:script>
		  //设置Ajax超时时间
   		Ext.Ajax.timeout=2000000;
		function onSelectNode(node) {
			query_deptId.setValue(node.id);
			if (vehicleTyre_adddeptId.rendered == true) {
				vehicleTyre_adddeptId.originalValue = node.id;
			} else {
				vehicleTyre_adddeptId.setValue(node.id);
			}
			if (vehicleTyre_teardeptId.rendered == true) {
				vehicleTyre_teardeptId.originalValue = node.id;
			} else {
				vehicleTyre_teardeptId.setValue(node.id);
			}
			onSearch();
		}

		var isNewRecord;
		var record;
		var first = 0;

		function checkboxClick() {
			var s = this.relateName + ".setValue(" + (this.checked ? this.trueValue : this.falseValue) + ")";
			eval(s);
		}

		function checkboxRenderer(v, p, r) {
			var trueValue = eval(this.id + '.trueValue');
			p.css += ' x-grid3-check-col-td';
			return '<div class="x-grid3-check-col'+((v==trueValue)?'-on':'')+' x-grid3-cc-'+this.id+'">&#160;</div>';
		}

		function datetimeRenderer(v, p, r) {
			if ((v == null) || (v.length < 19)) return v;
			var dateFormat = eval(this.id + '.format');
			if (typeof(v) == "string") {
				v = v.replace('T', ' ' );
				v = Date.parseDate(v, "Y-m-d H:i:s");
			}
			return v.format(dateFormat);
		}

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
		
		function stateRenderer(v, p, r) {
			var state = v;
			if(state == 2){
				v = "${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.5','历史')}";
			}else if(v == 1){
				v='${app:i18n_def('vmsoperation.vehicleTyre.page.4','正在使用')}';
			}else if(v == 0){
				v='${app:i18n_def('vmsoperation.vehicleTyre.page.3','报废')}';
			}else if(v == 3){
				v='${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.23','待用')}';
			}
			return v;
		}
		
		function teardownRemarkRenderer(v, p, r) {
			var state = v;
			if(state == 0){
				v='${app:i18n_def('vmsoperation.vehicleTyre.page.3','报废')}';
			}else if(v == 3){
				v='${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.23','待用')}';
			}else if(state == 2){
				v = "${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.101','已拆卸')}";
			}
			return v;
		}
		

		function comboBoxdoQuery(item) {
			if (item.isXType('combo')) {
				item.doQuery("", true);
			} else if (item.isXType('grid') || item.isXType('editorgrid')) {
				var model = item.getColumnModel();
				for(var i = 0; i < model.getColumnCount(); i++) {
					var column = model.getColumnById(model.getColumnId(i));
					var editor = column.editor;
					if (editor && editor.field.isXType('combo')) {
						editor.field.doQuery("", true);
					}
				}
			}
			if (item.items && item.items.getCount() > 0) {
				for(var i = 0; i < item.items.getCount(); i++) {
					comboBoxdoQuery(item.items.get(i));
				}
			}
		}

		function onUIShow() {
			var mainView = queryView.ownerCt;
			var queryHeight = queryView.getInnerHeight() + queryView.getFrameHeight();
			gridView.setPosition(0, queryHeight);
			gridView.setHeight(mainView.getInnerHeight() - queryHeight);

			comboBoxdoQuery(mainView);
			comboBoxdoQuery(editView);
		}

		function initComboBox() {
			if (!this.initialized) {
				this.initialized = true;
				var TYPE = Ext.data.Record.create([{name:this.valueField}, {name:this.displayField}]);
				var r = eval('new TYPE({' + this.valueField + ':"",' + this.displayField + ':"---"});');
				this.store.insert(0, r);
			}
		}

		function fillForm(item) {
			var fieldName = item.name;
			if (item.isXType('checkbox') && item.relateName) {
				fieldName = eval(item.relateName + ".name");
			}
			if (fieldName != undefined) {
				fieldName = fieldName.substring(fieldName.indexOf('.') + 1, fieldName.length);
				var value = record.data[fieldName];
				if (value != undefined) {
					if ((item.isXType('datefield') || item.isXType('timefield')) && (value.length == 19)) {
						value = value.replace('T', ' ' );
						value = Date.parseDate(value, "Y-m-d H:i:s");
						value = value.format(item.format);
					} else if (item.isXType('checkbox')) {
						value = (value == item.trueValue) ? true : false;
					}
					item.setValue(value);
				}
			}
			if (item.items && item.items.getCount() > 0) {
				for(var i = 0; i < item.items.getCount(); i++) {
					fillForm(item.items.get(i));
				}
			}
		}

		function setEditable(item, canEdit) {
			if (item.modifytable == "false") {
				if (canEdit == true) {
					item.enable();
				} else {
					item.disable();
				}
			}
			if (item.items && item.items.getCount() > 0) {
				for(var i = 0; i < item.items.getCount(); i++) {
					setEditable(item.items.get(i), canEdit);
				}
			}
		}

		function onSearch() {
			if(query_deptId.getValue() == ""){
				Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.page.34','请选择网点！')}")
				return;
			}
			if (queryView.getForm().isValid() == false) {
				Ext.MessageBox.alert('${app:i18n('prompt')}', '${app:i18n('validateError')}');
			} else {

				gridView.getStore().baseParams = queryView.getForm().getValues();
				gridView.getStore().baseParams["limit"] = pagingBar.pageSize;
				gridView.getStore().load();
			}
		}

		function onAddSearch(){
			usegridView.getStore().baseParams["limit"] = usepagingBar.pageSize;
			usegridView.getStore().baseParams["vehicleId"] = vehicleTyre_vehicleId.getValue();
			usegridView.getStore().load();
		}


		function setTitle(title) {
			if ((title != null) && (title.length > 0)) {
				var ch = title.substr(title.length - 1, 1);
				if (((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z'))) {
					title = title + ' ';
				}
			}
			//editView.setTitle(title + "${app:i18n('vehicleTyre')}");
			editView.setTitle("${app:i18n('vehicleTyre')}");
		}

		function setTearTitle(title) {
			if ((title != null) && (title.length > 0)) {
				var ch = title.substr(title.length - 1, 1);
				if (((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z'))) {
					title = title + ' ';
				}
			}
			tearView.setTitle(title);
		}

		function onAdd() {
			if(query_deptId.getValue() == ""){
				Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.page.34','请选择网点！')}")
				return;
			}

			setTitle(btnEdit.text);
			isNewRecord = true;
			editForm.getForm().reset();
			usegridStore.removeAll();
			Ext.getCmp('vehicleCbx').store.baseParams = {'deptId':query_deptId.getValue()};
			setEditable(editView, true);
			editView.show();
			if(first == 0){
				usepagingBar.addText("${app:i18n_def('vmsoperation.vehicleTyre.page.35','在用轮胎记录')}");
				first = 1;}

		}

		function onTear(){
		
			if (usegridView.getSelectionModel().getSelections().length != 1) {
				Ext.MessageBox.alert('${app:i18n('prompt')}','${app:i18n('selectOneRecord')}');
				return;
			}
			var record = usegridView.getSelectionModel().getSelections()[0];
			if(record.data.vehicle.vehicleCode == null){
				Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.page.36','车牌号不存在')}");
				return;
			}
			
			btnTearSave.enable();
			vehicleTyre_position.setDisabled(true);
			vehicleTyre_teardownMiles.setValue('') ;
			
			tearForm.getForm().reset();
			setEditable(tearView, true);
			tearView.show();
			vehicleTyre_teartyreId.setValue(record.data.tyreId);
			tearvehicle.setValue(record.data.vehicle.vehicleCode);
			setTearTitle(btnTear.text);
			if (record.data.position) {
				vehicleTyre_position.setValue(record.data.position);
			}
			tearvehicleTyre_tyreNo.setValue(record.data.tyreNo);
			vehicleFactoryDate.setValue(record.data.vehicle.factoryDt.substr(0, 10));
		}

				  //-------------------时间格式转换--时间-----------------
		   function formatDatesShortDate(v){
		      		if (v != null&& typeof(v) == "string") {
		      			v = v.substr(0,10);
		      			return Date.parseDate(v, "Y-m-d");
					 }
						return v;
		   }


		function onEdit() {
			if (usegridView.getSelectionModel().getSelections().length != 1) {
				Ext.MessageBox.alert('${app:i18n('prompt')}','${app:i18n('selectOneRecord')}');
				return;
			}

			isNewRecord = false;
			modifyForm.getForm().reset();
			setEditable(modifyView, false);
			modifyView.show();
			record = usegridView.getSelectionModel().getSelections()[0];
			fillForm(modifyForm);
			modifyvehicleFactoryDate.setValue(record.data.vehicle.factoryDt.substr(0, 10));
			modifyvehicle.setValue(record.data.vehicle.vehicleCode);
			modifyvehicleTyre_tyreTypeNo.setValue(record.data.vehicle.tyreType.dataDesc);
			modifyvehicleTyre_vehicleId.setValue(record.data.vehicle.id);
			modifyvehicleTyre_tyreTypeId.setValue(record.data.vehicle.tyreType.id);
		}

		function onDelete() {
			var records = usegridView.getSelectionModel().getSelections();
			if (records.length < 1) {
				Ext.MessageBox.alert('${app:i18n('prompt')}', '${app:i18n('selectDeleteData')}');
			} else {
				Ext.MessageBox.confirm('${app:i18n('prompt')}','${app:i18n('confirmDeleteData')}', deleteRecord);
			}
		}

		function deleteRecord(result) {
			if (result == 'yes') {
				var records = usegridView.getSelectionModel().getSelections();
				var tyreIds = '';
				for(var i = 0; i < records.length; i++) {
				  tyreIds += records[i].data.tyreId + ',';
				}

				Ext.Ajax.request({params: {tyreIds: tyreIds},
					url: "deleteVehicleTyres.action",
					success: function(response) {
						var resp = Ext.util.JSON.decode(response.responseText);
						if (resp.status == "ok") {
							Ext.MessageBox.alert('${app:i18n('prompt')}','${app:i18n('deleteSuccess')}');
							usegridView.getStore().load();
						} else {
							Ext.MessageBox.alert('${app:i18n('deleteFailure')}', resp.status);
						}
					}
				});
			}
		}

		function onReset() {
			editForm.getForm().reset();
			usegridView.getStore().loadData([]);
		}

		//装用
		function onSave() {
			if (isNewRecord == false) {
				setEditable(editView, true);
			}
			if(editForm.getForm().isValid()){
				var installDt = vehicleTyre_installDt.getValue();
				//装用日期不能小于轮胎的入库时间
				var tryeInTm = vehicleTyre_tryeInTm.getValue().split("T")[0];
				var tryeInTmDt = Date.parseDate(tryeInTm, "Y-m-d");
				
				if(installDt < tryeInTmDt ){
					Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.page.301','装用日期不能小于轮胎的入库时间')}");
					return;
				}
				
				//装用日期不能大于当前日期
				if(installDt > new Date()){
					Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.page.37','装用日期不能大于当前日期')}");
					return;
				}
				editForm.saveVehicleTyre();
			}
			
		}

		//轮胎记录的编辑
		function onModifySave(){

			if(modifyForm.getForm().isValid()){
				//装用日期不能大于当前日期
				var installDt = modifyvehicleTyre_installDt.getValue();
				if(installDt > new Date()){
					Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.page.37','装用日期不能大于当前日期')}");
					return;
				}
				modifyForm.modifyVehicleTyre();
			}
		}



		function onTearSave(){
			if(tearForm.getForm().isValid()){
				
				var tearDt = vehicleTyre_teardownDt.getValue();
				if(tearDt > new Date()){
					Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.page.38','拆卸日期不能大于当前日期')}");
					return;
				}
				var record = usegridView.getSelectionModel().getSelections()[0];
				//拆卸时间不能小于装用时间的验证
				var installDt = record.data.installDt;
				if(installDt != null){
					installDt = formatDatesShortDate(installDt);
					tearDt = formatDatesShortDate(tearDt);
					if(installDt > tearDt){
						Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.page.39','拆卸时间不能小于装用时间')}");
						return;
					}
				}
				
				//判断是否有换表操作
				var totalMiles = record.data.totalMiles;
				if(totalMiles > 0){ //总里程大于0 说明有换表操作
					;
				}else{
					//拆卸里程不能大于装用里程的验证
					var installMiles = parseFloat(record.data.installMiles);
					var tearMiles = parseFloat(vehicleTyre_teardownMiles.getValue());
					if(installMiles > tearMiles){
						Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.page.40','拆卸里程必须大于装用里程')}");
						return;
					}
				}
				
				//判断是否是vms2.2以前的版本
				var record = usegridView.getSelectionModel().getSelections()[0];
				var version = record.data.version;
				if(version == '1'){
					var teardownRemark = vehicleTyre_teardownRemark.getValue() ;
					if(teardownRemark != '0'){
						Ext.MessageBox.alert('${app:i18n('prompt')}', '${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.27','Vms2.2版本以前的车辆轮胎使用记录，拆卸时拆卸备注只能选报废')}');
						return ;
					}
				}
				
				tearForm.tearVehicleTyre();
				
				}
		}

		//拆卸轮胎成功时提示信息
		function showSaveTearSuccessInfo(form, action) {
			if (action.result.status == "ok") {
				Ext.MessageBox.alert('${app:i18n('prompt')}', '${app:i18n('saveSuccess')}');
				tearView.hide();
				onAddSearch();
				onSearch();
			} else {
				Ext.MessageBox.alert('${app:i18n('saveFailure')}', action.result.status);
				if (isNewRecord == false) {
					setEditable(editView, false);
				}
			}
		}

			//修改再用轮胎信息成功时的信息
		function showSaveEditSuccessInfo(form, action) {
			if (action.result.status == "ok") {
				Ext.MessageBox.alert('${app:i18n('prompt')}', '${app:i18n('saveSuccess')}');
				modifyView.hide();
				onAddSearch();
			} else {
				Ext.MessageBox.alert('${app:i18n('saveFailure')}', action.result.status);
				if (isNewRecord == false) {
					setEditable(modifyView, false);
				}
			}
		}

		function showSaveSuccessInfo(form, action) {
			if (action.result.status == "ok") {
				Ext.MessageBox.alert('${app:i18n('prompt')}', '${app:i18n('saveSuccess')}');
				//editView.hide();
				vehicleTyre_tyrePrice.setValue(null);
				vehicleTyre_brandModel.setValue(null);
				vehicleTyre_installRemark.setValue(null);
				editvehicleFactoryDate.setValue(null);
				vehicleTyre_tyreNo.setValue(null);
				vehicleTyre_installDt.setValue(null);
				vehicleTyre_installMiles.setValue(null);
				//装用完成后轮胎库存设置为空
				Ext.getCmp("tryestorre_id").setValue('');
				
				onAddSearch();
				onSearch();

			} else if(action.result.status == "no"){
				Ext.MessageBox.alert('${app:i18n('prompt')}', '${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.28','在用轮胎数应小于车胎数，请先拆卸在用轮胎')}');
			} else {
				Ext.MessageBox.alert('${app:i18n('saveFailure')}', action.result.status);
				if (isNewRecord == false) {
					setEditable(editView, false);
				}
			}
		}

		function showSaveFailureInfo(form, action) {
			if (action.failureType == "client") {
				Ext.MessageBox.alert('${app:i18n('prompt')}', '${app:i18n('validateError')}');
			} else {
				Ext.MessageBox.alert('${app:i18n('prompt')}', '${app:i18n('saveFailure')}');
			}
			if (isNewRecord == false) {
				setEditable(editView, false);
			}
		}

			function onBeforeShowTriggerWindow(searchParam){
				if(query_deptId.getValue()!=null){
					searchParam['vehicle.department.id'] = query_deptId.getValue();
					searchParam.start = 0;
					searchParam.limit= 15;
				}
			}

			   function formatDatesShortDate(v){
      		if (v != null&& typeof(v) == "string") {
      			v = v.substr(0,10);
      			return Date.parseDate(v, "Y-m-d");
			 }
				return v;
   }

   	function showVehicleCode(r){
   		return r.vehicleCode;
   	}

   	function showModelWheel(r){
   		return r.brandModel + r.wheelbase;
   	}

   	function showTyreDesc(r){
   		if(r.tyreType != null)
   			return r.tyreType.dataDesc;
   		else
   			return "";
   	}

	function showExportResult(form, action) {
			var fileName = action.result.fileName;
			var url = "downloadTearTyre.action?fileName=" + fileName;
			window.location = url;
	}

   	function onExport(){
   		if(query_deptId.getValue() == ""){
				Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.page.34','请选择网点！')}")
				return;
			}

		if (queryView.getForm().isValid() == false) {
				Ext.MessageBox.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}", "${app:i18n_def('vmsoperation.vehicleTyre.page.41','格式错误')}");
			} else {
				queryView.getForm().doAction('submit',{url:"exportTearTyre.action",success:showExportResult,failure:function(form, action){Ext.MessageBox.alert('ERROR',action.result.error);}});
			}
	}

	function tearReset(){
		tearForm.getForm().reset();
		var record = usegridView.getSelectionModel().getSelections()[0];
		vehicleTyre_teartyreId.setValue(record.data.tyreId);
		tearvehicle.setValue(record.data.vehicle.vehicleCode);
		setTearTitle(btnTear.text);
		tearvehicleTyre_tyreNo.setValue(record.data.tyreNo);
		vehicleFactoryDate.setValue(record.data.vehicle.factoryDt.substr(0, 10));
	}
	//有重复命名,以后开发请注意
	
	//当拆卸日期改变时触发该事件
	function setTeardownMiles(dt){
		var teardownDt = vehicleTyre_teardownDt.getRawValue();
		if(teardownDt && teardownDt.trim() == ''){
			return  ;
		}
		var vehicleId = vehicleTyre_vehicleId.getValue() ;
		Ext.Ajax.request({ 
			  	params: {'vehicleTyre.teardownDt': teardownDt,'vehicleTyre.deptId':query_deptId.getValue(),'vehicleTyre.vehicle.id':vehicleId},
				url: "queryTeardownMiles.action",
				success: function(response) {
					var resp = Ext.util.JSON.decode(response.responseText);
					if (resp.success) {
							if(resp.status.length>3 && resp.status.substring(0,2)=='ok'){
								var teardown_miles = resp.status.split(',')[1] ;
								vehicleTyre_teardownMiles.setValue(teardown_miles);
								btnTearSave.enable() ;
							}else{
								btnTearSave.disable() ;
								vehicleTyre_teardownMiles.setValue('');
								Ext.MessageBox.alert('${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}', resp.status);
							}
					} 
				},
				failure:ajaxRequestFailure
			});
	}
	
	//当装用日期改变时触发该事件
	function setInstallMiles(dt){
		var installDt = vehicleTyre_installDt.getRawValue();
		if(installDt && installDt.trim() == ''){
			return  ;
		}
		var vehicleId = Ext.getCmp("vehicleCbx").getValue() ;
		//alert(vehicleId +","+installDt) ;
		Ext.Ajax.request({ 
			  	params: {'vehicleTyre.installDt': installDt,'vehicleTyre.deptId':query_deptId.getValue(),'vehicleTyre.vehicle.id':vehicleId},
				url: "queryInstallMiles.action",
				success: function(response) {
					var resp = Ext.util.JSON.decode(response.responseText);
					if (resp.success) {
							if(resp.status.length>3 && resp.status.substring(0,2)=='ok'){
								var install_miles = resp.status.split(',')[1] ;
								vehicleTyre_installMiles.setValue(install_miles);
							}else{
								vehicleTyre_installMiles.setValue('');
								Ext.MessageBox.alert('${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}', resp.status);
							}
					} 
				},
				failure:ajaxRequestFailure
			});
	}
	
	
	//ajax请求失败处理事件
	function ajaxRequestFailure(response) {
		  if (response.status == 0) {
		      Ext.Msg.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}", "${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.29','无法连接到服务器，请检查网络是否正常')}");
		  } else if (response.status == -1) {
		      Ext.Msg.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}", "${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.30','服务器处理超时，请稍后再试')}");
		  } else {
				Ext.Msg.alert("${app:i18n_def('vmsoperation.vehicleTyre.page.24','提示')}","${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.31','系统出现异常,请与管理员联系')}");
			}
		};
	</ext:script>
</ext:ui>