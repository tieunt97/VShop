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

	public function getAllProductsByProvider($provider_id) {
		$products = DB::table('products')->select('id','product_name','main_image','base_price')->where('provider', $provider_id)->paginate(Consts::NUM_PRODUCT_IN_PAGE);
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
			'star_number'			=>	array_sum($products) / count($products),
			'star_count'			=>	count($products)
		];
		}else {
			$starInfos = [
			'star_number'			=>	0,
			'star_count'			=>	0
		];
		}
		return $starInfos;
	}

	public function getEvaluations($product_id, $limit) {
		if ($limit == Consts::GET_ALL) {
			$evaluations = DB::table('evaluations')->select('customer_id','title','content','star_number','created_at')->where('product_id','=',$product_id)->paginate(Consts::NUM_EVALUATION_IN_TIME);
		}else {
			$evaluations = DB::table('evaluations')->select('customer_id','title','content','star_number','created_at')->where('product_id','=',$product_id)->limit($limit)->get();
		}
		
		if (count($evaluations) == 0) return [];
		foreach ($evaluations as $evaluation) {
			$customer = DB::table('users')->select('name')->where('id','=',$product_id)->pluck('name')->toArray();
			$evaluation->customer_name = $customer[0];
		}
		return $evaluations;
	}

	public function getProductSpecificationsOfProduct($id) {
		$productAttribute = DB::table('attribute_values')
		->select('attribute_values.value','category_attributes.category_name')
		->join('product_attributes', 'product_attributes.id', '=', 'attribute_values.attribute_product_id')
		->join('category_attributes','category_attributes.id','=','product_attributes.category_attribute_id')
		->where('attribute_values.product_id','=',$id)
		->get();

		return $productAttribute;
	}

	public function sortProducts($request, $productTyleId) {
		$query = DB::table('products')->where('product_type_id','=',$productTyleId);
		$sort = $request->sort;
		switch ($sort) {
			case 'new':
				$query = $query->orderBy('created_at','desc');
				break;
			case 'top':
				$query = $query->orderBy('base_price','desc');
				break;
			case 'last':
				$query = $query->orderBy('base_price','asc');
				break;
			default:
				break;
		}
		$products = $query->select('id','product_name','main_image','base_price')->paginate(Consts::NUM_PRODUCT_IN_PAGE);
		return $products;
	}

	public function getProductsbyStarNumberLarger($request, $productTyleId) {
		$star_number = $request->star_number;
		$query = DB::table('products')->where('product_type_id','=',$productTyleId);
		$products = $query->join('evaluations','products.id','=','evaluations.product_id')->groupBy('product_id')->select('products.id', 'product_name','main_image','base_price', DB::raw('avg(star_number) as star_number, count(product_id) as star_count'))->where('star_number', '>=', $star_number)->orderBy('star_number','desc')->paginate(Consts::NUM_PRODUCT_IN_PAGE);
		return $products;
	}

	public function getStarNumberDetailOfProduct($product_id) {
		$star_detail = DB::table('evaluations')->select(DB::raw('count(star_number) as number, star_number'))->where('product_id','=',$product_id)->groupBy('star_number')->get()->toArray();
		return $star_detail;
	}

	public function getTest() {
		// return "1";
		// $evaluations = DB::table('evaluations')->groupBy('product_id')->select(DB::raw('avg(star_number) as star_number, count(product_id) as star_count'), 'product_id')->get();
		$query = DB::table('products')->where('product_type_id','=', 2);
		$query = $query->join('evaluations','products.id','=','evaluations.product_id')->groupBy('product_id')->select( DB::raw('avg(star_number) as star_number, count(product_id) as star_count'), 'product_id')->select('products.*')->get();
		return $query;
	}



}