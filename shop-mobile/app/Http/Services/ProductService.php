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

	public function getStarNumberOfProduct($product_id) {
		$products = DB::table('evaluations')->where('product_id','=',$product_id)->pluck('star_number')->toArray();
		$starInfos = [];
		if(count($products) != 0 ) {
			$starInfos = [
			'star_number'	=>	array_sum($products) / count($products),
			'numbe'			=>	count($products)
		];
		}else {
			$starInfos = [
			'star_number'	=>	0,
			'numbe'			=>	0
		}
		return $starInfos;
	}


}