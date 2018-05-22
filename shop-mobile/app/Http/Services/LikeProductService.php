<?php
namespace App\Http\Services;
use Carbon\Carbon;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
class LikeProductService {

	public function getLikeOfCustomer($user_id) {
		$likes = DB::table('like_product_lists')->where('customer_id','=',$user_id)->pluck('product_id')->toArray();
		return $likes;
	}

	public function checkCustommerLikedProduct($customer_id, $product_id) {	
		$customerIds = DB::table('like_product_lists')->select('customer_id')->where('product_id', '=', $product_id)->pluck('customer_id')->toArray();	
		if (count($customerIds) == 0) {	
			return false;	
		}else {	
			foreach ($customerIds as $customerId) {	
				if ($customerId == $customer_id) {	
					return true;	
				}	
			}	
			return false;	
		}	
	}

}