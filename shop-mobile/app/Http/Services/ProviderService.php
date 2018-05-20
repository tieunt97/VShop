<?php

namespace App\Http\Services;
use Carbon\Carbon;
use App\Consts;
use App\Provider;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;

class ProviderService {
	public function getProvidersInfo() {
		$providers = Provider::select('id', 'name','icon')->get()->toArray();
		return $providers;
	}
}