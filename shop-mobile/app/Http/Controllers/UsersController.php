<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class UsersController extends AppBaseController
{
    public function updateUser(Request $request) {
    	$currentUser = Auth::user();
        $params = $request->all();
        DB::beginTransaction();
        try {
            DB::table('users')->where('id','=', $currentUser->id)->update(['address' => $params['address'],'name' => $params['name'],'phone_number' => $params['phone_number']]);    
            DB::commit();
            return $this->sendResponse(null, 'updated');
         } catch (\Exception $e) {
            DB::rollBack();
            return $this->sendError(null, $e->getMessage());
        }
    }
}
