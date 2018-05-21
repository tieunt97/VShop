<?php

namespace App\Http\Controllers\Auth;

use Illuminate\Http\Request;
use App\Http\Controllers\AppBaseController;
use Illuminate\Support\Facades\Auth;

class LoginController extends AppBaseController
{

    public function login(Request $request) {
    	$data = [
    		'email'	=> $request->email,
    		'password'	=> $request->password
    	];

    	if (Auth::attempt($data)) {
    		$user = Auth::user();
    		return $this->sendResponse($user, 'login success');
    	}else {
    		return $this->sendResponse(false, 'login failure');
    	}
    }

    public function logout() {
        Auth::logout();
        return $this->sendResponse(true,'logout success!');
    }

    public function showLoginForm() {
        return $this->sendResponse(false,'you need to login!');
    }
}
