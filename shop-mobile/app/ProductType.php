<?php

namespace App;

use App\VersionedModel;

class ProductType extends VersionedModel
{	
	public $table = 'product_types';
    public static $tablename = 'product_types';
    public static function getTableName() { return self::$tablename; }
}
