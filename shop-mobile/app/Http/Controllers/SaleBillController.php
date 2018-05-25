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
        $results = [];
        $products = explode(',', $request->products);
        for($i = 0; $i < count($products); $i++) {
            if($i%2 == 0) {
                array_push($results, ['product_id' => $products[$i],'amount' => $products[$i+1]]);
            }
        }
        $request->products = $results;
        $orderBook = $this->saleBillService->orderBook($request);
        return $this->sendResponse($orderBook, 'updated !');
    }

    
}
