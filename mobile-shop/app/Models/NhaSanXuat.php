<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class NhaSanXuat extends Model
{
    public $table = 'nha_san_xuats';
    public $fillable = [
    	'id',
    	'ten_nha_sx'
    ];

}
