<?php

namespace App\Http\Services;
use Carbon\Carbon;
use App\Consts;
use App\SaleBill;
use App\SaleDescription;
use App\User;
use App\Product;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;

class ShipperService {
	public function getOrderListIsWaitting() {
		$orders = SaleBill::select('id','customer_id','destination_address','ship_fee')
		->where('status_order', '=', 'waitting')->get();
		foreach ($orders as $order) {
			$customer_info = User::select('name','phone_number')->where('id', '=' , $order->customer_id)->get();
			$order->customer_info = $customer_info;
			$order->order_detail = $this->getProductInfoOfSaleBill($order->id);
		}
		return $orders;
	}


	public function getProductInfoOfSaleBill($sale_bill_id) {
		$info = [];
		$total = 0;
		$orderInfos = SaleDescription::select('product_id','amount')->where('sale_bill_id' , '=', $sale_bill_id)->get();
		foreach ($orderInfos as $orderInfo) {
			$products = Product::select('product_name','base_price')->where('id' ,'=',$orderInfo->product_id)->first();
			$info[] = [
				'product_name' => $products->product_name,
				'base_price'   => $products->base_price,
				'amount'		=> $orderInfo->amount
			];
			$total += $products->base_price*$orderInfo->amount;
		}

		return ['info' => $info,'total' => $total];
		
	}
}