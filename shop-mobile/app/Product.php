<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Product extends VersionedModel
{
    protected $casts = [
    	'sub_images' => 'array'
    ];
}
