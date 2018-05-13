<?php

namespace App\Http\Services;
use Carbon\Carbon;
use App\Consts;
use App\SaleBill;
use App\User;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;

class SaleBillService {
	public function getSaleBillIsPending() {
		$saleBills = SaleBill::select('id', 'customer_id', 'delivery_date', 'destination_address','ship_fee', 'book_date')->where('status_order', '=', 'pending')->paginate(Consts::NUM_PRODUCT_IN_PAGE);
		foreach ($saleBills as $saleBill) {
			$user_info = User::select('name','phone_number')->where('id', '=', $saleBill->customer_id)->get();
			$saleBill->user_name = $user_info[0]['name'];
			$saleBill->phone_number = $user_info[0]['phone_number'];
		}

		return $saleBills;
	}
}