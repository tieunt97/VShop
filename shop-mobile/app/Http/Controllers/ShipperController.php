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

    public function getProductInfoOfSaleBill($sale_bill_id) {
    	// return "1";
    	$orderInfo = $this->shipperService->getProductInfoOfSaleBill($sale_bill_id);
    	return $this->sendResponse($orderInfo, '200');
    }

}
