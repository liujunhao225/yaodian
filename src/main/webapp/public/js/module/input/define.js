Ext.define('inputModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		type : 'int'
	},{
		name : 'medicineId',
		type : 'int'
	},{
		name:'leftNum',
		type:'int'
	},{
		name:'medicineBasePrice',
		type:'float'
	},{
		name:'medicineSellsPrice',
		type:'float'
	},{
		name:'medicineName',
		type:'string'
	},{
		name:'medicineBarCode',
		type:'string'
	}]
});
	
