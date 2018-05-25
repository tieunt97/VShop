<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\ShipperService;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class ShipperController extends AppBaseController
{
    protected $shipperService;

    public function __construct(ShipperService $shipperService) {
    	$this->shipperService = $shipperService;
    }

    public function getListWaitingOrder() {
    	$orderList = $this->shipperService->getOrderListIsWaitting();
    	return $this->sendResponse($orderList, '200');
    }

    public function getMyOrderList() {
        $orderList = $this->shipperService->getMyOrderList();
        return $this->sendResponse($orderList, '200');
    }

    public function getMyHistoryOrderList() {
        $orderList = $this->shipperService->getMyHistoryOrderList('shipped');
        return $this->sendResponse($orderList, '200');
    }

    

    public function getProductInfoOfSaleBill($sale_bill_id) {
    	// return "1";
    	$orderInfo = $this->shipperService->getProductInfoOfSaleBill($sale_bill_id);
    	return $this->sendResponse($orderInfo, '200');
    }

    public function updateStatusOrderToShipped(Request $request) {
         $user = Auth::user();
        $sale_bill_id = $request->sale_bill_id;
         DB::beginTransaction();
        try {
            DB::table('sale_bills')->where('id','=', $sale_bill_id)->update(['shipper_id' => $user->id,'status_order' => 'shipped']);    
            DB::commit();
            return $this->sendResponse(null, 'updated');
         } catch (\Exception $e) {
            DB::rollBack();
            return $this->sendError(null, $e->getMessage());
        }
    }

    public function receiveOrderByShipper(Request $request) {
        $user = Auth::user();
        $sale_bill_id = $request->sale_bill_id;
         DB::beginTransaction();
        try {
            DB::table('sale_bills')->where('id','=', $sale_bill_id)->update(['shipper_id' => $user->id,'status_order' => 'received_order']);    
            DB::commit();
            return $this->sendResponse(null, 'updated');
         } catch (\Exception $e) {
            DB::rollBack();
            return $this->sendError(null, $e->getMessage());
        }
    }

}
