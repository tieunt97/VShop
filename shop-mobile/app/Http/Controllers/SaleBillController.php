<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\SaleBillService;

class SaleBillController extends AppBaseController
{
    protected $saleBillService;

    public function __construct(SaleBillService $saleBillService) {
    	$this->saleBillService = $saleBillService;
    }

    public function getSaleBillisPending() {
    	$saleBills = $this->saleBillService->getSaleBillIsPending();
    	return $this->sendResponse($saleBills, '200');
    }

    public function getSaleBillDescriptionBySaleBillId($saleId) {
    	$saleDes = $this->saleBillService->getSaleDescriptionBySaleBillId($saleId);
    	return $this->sendResponse($saleDes, '200');
    }

    public function receiveOrderByShipper() {

    }

    public function orderBook(Request $request) {
        $products = $request->products;
        $orderBook = $this->saleBillService->orderBook($request);
        return $this->sendResponse($orderBook, '200');
    }

    
}
