Ext.define('inputModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		type : 'int'
	},{
		name : 'medicineId',
		type : 'int'
	},{
		name:'medicineSellsPrice',
		type:'float'
	},{
		name:'tempcount',
		type:'int'
	},
	{
		name:'medicineName',
		type:'string'
	},{
		name:'medicineBarCode',
		type:'string'
	}]
});
	
