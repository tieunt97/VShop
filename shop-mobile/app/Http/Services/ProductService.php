<?php

namespace App\Http\Services;
use Carbon\Carbon;
use App\Consts;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;

class ProductService {
	public function getAllProductsByType($type_id) {
		$products = DB::table('products')->select('id','product_name','main_image','base_price')->where('product_type_id', $type_id)->paginate(Consts::NUM_PRODUCT_IN_PAGE);
		return $products;
	}

	public function searchProductBy($keyword) {
		$product = DB::table('products')->select('id','product_name','main_image','base_price')
		->where('product_name','like','%'.$keyword.'%')
		->orWhere('description','like','%'.$keyword.'%')
		->orWhere('base_price','like','%'.$keyword.'%')
		->paginate(Consts::NUM_PRODUCT_IN_PAGE);
		return $product;
	}
}