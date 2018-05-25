<?php

namespace App\Http\Services;
use Carbon\Carbon;
use App\Consts;
use App\SaleBill;
use App\SaleDescription;
use App\User;
use App\Product;
// use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;

class SaleBillService {
	public function getSaleBillIsPending() {
		$saleBills = SaleBill::select('id', 'customer_id', 'delivery_date', 'destination_address','ship_fee', 'book_date')->where('status_order', '=', 'pending')->paginate(Consts::NUM_SALE_BILL_IN_PAGE);
		foreach ($saleBills as $saleBill) {
			$user_info = User::select('name','phone_number')->where('id', '=', $saleBill->customer_id)->get();
			$saleBill->user_name = $user_info[0]['name'];
			$saleBill->phone_number = $user_info[0]['phone_number'];
		}

		return $saleBills;
	}

	public function getSaleDescriptionBySaleBillId($sale_bill_id) {
		$saleDess = SaleDescription::select('product_id', 'amount')->where('sale_bill_id', '=', $sale_bill_id)->get();
		foreach ($saleDess as $saleDes) {
			$productInfo = Product::select('product_name','base_price','unit')->where('products.id','=',$saleDes->product_id)->get()->toArray();
			$saleDes->product_info = $productInfo[0];
		}
		return $saleDess;
	}

	public function orderBook($request) {
        $sale_bill_id = $this->createSaleBill($request->destination_address);
		$products = $request->products;
		foreach ($products as $product) {
			DB::beginTransaction();
        try {
            DB::table('sale_descriptions')->insert([
            	'product_id'	=>	$product['product_id'],
            	'sale_bill_id'	=>	$sale_bill_id,
            	'amount'	=>	$product['amount'],
            	'created_at'	=> Carbon::now(),
            ]);    
            DB::commit();
         } catch (\Exception $e) {
            DB::rollBack();
            return $e;
        }
		}

		return true;
	}

	public function createSaleBill($destination_address) {
		$user = Auth::user();
		DB::beginTransaction();
        try {
            DB::table('sale_bills')->insert([
            	'customer_id'	=>	$user->id,
            	'status_order'	=>	'pending',
            	'delivery_date'	=>	Carbon::now(),
            	'destination_address'	=> $destination_address,
            	'ship_fee'			=> 2,
            	'book_date'		=>	Carbon::now()
            ]);    
            DB::commit();
            return DB::table('sale_bills')->orderBy('id','desc')->pluck('id')->first();
         } catch (\Exception $e) {
            DB::rollBack();
            return false;
        }

	}
}