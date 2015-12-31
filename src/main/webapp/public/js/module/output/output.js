/**
 * medicineInfo
 */
var PAGE_SIZE = 20;
var INPUTLOG_SAVE_URL = "/oscar/sells/add.do";
var INPUTLOG_FINDMEDICINE_URL="/oscar/input/findone.do";
Ext.onReady(function() {
    Ext.QuickTips.init();
    var inputStore = Ext.create('Ext.data.Store', {
        model: 'inputModel'
    });
    
    var infoGrid = Ext.create('Ext.grid.Panel', {
        store: inputStore ,
        region: 'south',
        renderTo: 'gridpanel',
        height:800,
        forceFit: 1,
        viewConfig: {
            emptyText: '&nbsp;&nbsp;没有相关的记录'
        },
        columns: [
        {
            dataIndex: 'id',
            hidden:true
        },
        {
        	dataIndex:'medicineId',
        	hidden:true
        },
        {
            text: '药品名称',
            dataIndex: 'medicineName'
        },
        {
        	text: '条码',
        	dataIndex: 'medicineBarCode'
        	
        },
        {
            text: '数量',
            dataIndex: 'tempcount',
            renderer:function(val, meta, record){
            	var count= record.data.tempcount;
            	if(count==0){
            		return 1;
            	}else{
            		return record.data.tempcount;
            	}
            }
            
        },
        {
            text: '销售价',
            dataIndex: 'medicineSellsPrice'
        }, {
            header: '操作',
            menuDisabled: true,
            sortable: false,
            xtype: 'actioncolumn',
            items: [{
                icon: '/oscar/public/images/common/delete.gif',
                tooltip: '删除',
                handler: function(grid, rowIndex, colIndex) {
                            var _store = grid.getStore();
                            var model = grid.getStore().getAt(rowIndex);
                            _store.remove(rowIndex);
                            Ext.Ajax.request({
                                url: INPUTLOG_DELETE_URL,
                                params: {
                                	medicineid: model.data.medicineId
                                },
                                success: function(response) {
                                	
                                },
                                failure: function() {
                                    Ext.Msg.alert('操作提示', "删除失败！");
                                }
                            });
                        }
            }
           ]
        }],
        dockedItems: [
        {
            xtype: 'toolbar',
            dock: 'top',
            items: [
                    '条码',{
                    	xtype:'textfield',id:'tbarcode',width:120,
                    	listeners:{
                    		'change':function(item,newValue,oldValue,e){
                    			if(newValue !=null && newValue !=''){
                    			var existFlag = false;
                    			var totalmoney = 0;
                    			inputStore.each(function(record) {
                    			   if(record.get('medicineBarCode')==newValue){
                    				   if(record.get('tempcount')==0){
                    					   record.set('tempcount',1);
                    				   }
                    				   record.set('tempcount',record.get('tempcount')+1);
                    				   Ext.getCmp("totalMoney").setValue();
                    				   existFlag = true;
                    				   inputStore.each(function(record) {
   	                      				if(record.get('tempcount')==0){
   	                      					   record.set('tempcount',1);
   	                      				}
   	                      				totalmoney = totalmoney+record.get('tempcount')*record.get('medicineSellsPrice');
   	                      				Ext.getCmp("totalMoney").setValue(totalmoney);
   	                      				console.log("totalmoney:"+totalmoney);
   	                      			});
                    			   }
                    			});
                    			if(existFlag==false){
        	            				Ext.Ajax.request({
        	            					url: INPUTLOG_FINDMEDICINE_URL,
        	            					params: { 
        	            						barCode:newValue
        	            					},
        	            					success: function(response){
        	            				        var text = response.responseText;
        	            				        console.log(text);
        	            				        var r = Ext.JSON.decode(text);
        	            				        inputStore.insert(0, r);
        	            				        inputStore.each(function(record) {
        	                      				if(record.get('tempcount')==0){
        	                      					   record.set('tempcount',1);
        	                      				}
        	                      				totalmoney = totalmoney+record.get('tempcount')*record.get('medicineSellsPrice');
        	                      				Ext.getCmp("totalMoney").setValue(totalmoney);
        	                      				console.log("totalmoney:"+totalmoney);
        	                      			});
        	            				    }
        	            			});
                    			
                    		}
                    		item.reset();	
                    	}
                    	}
                    }
                    },"->","总价:",{
                    	xtype:'textfield',id:"totalMoney",width:120,readOnly:true
                    },{
                    	xtype : 'button',
    					text : '确认',
    					listeners:{
    						click:function(){
    							var arr = new Array();
    							inputStore.each(function(record) {
    								console.log(record.data);
    								arr.push(record.data);
                     			});
    							console.log(arr);
    							console.log(Ext.JSON.encode(arr));
    							Ext.Ajax.request({
	            					url: INPUTLOG_SAVE_URL,
	            					params: { 
	            						'allRecord': Ext.JSON.encode(arr)
	            					},
	            					success: function(response){
	            				      Ext.Msg.alert("提示信息","成功");
	            				      Ext.getCmp("totalMoney").setValue(0);
	            				      inputStore.removeAll();
	            				    }
	            			});
    						}
    					}
                    }
                  ]
        }]
    });
   
    
});