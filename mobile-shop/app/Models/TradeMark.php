<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class TradeMark extends Model
{
    public $table = 'thuong_hieus';
    public $fillable = [
    	'id',
    	'ten_thuong_hieu'
    ];

}
