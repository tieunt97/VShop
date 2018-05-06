<?php

namespace App\Http\Services;
use Carbon\Carbon;
use App\Consts;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
use App\ProductType;

class TypeProductService {
	public function getAllTypes() {
		return ProductType::all()->toArray();
	}
}