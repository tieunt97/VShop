<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
use App\User;
use Carbon\Carbon;
class SaleBillsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $sale_bills = [];
        $faker = Faker::create();
        $customers = User::select('id')->where('level', '=', 1)->pluck('id')->toArray();
        $RECORD_COUNT =30;

        for ($sale_bill_id = 1; $sale_bill_id <= $RECORD_COUNT; $sale_bill_id ++) {
        	$sale_bills[] = [
        		'id' => $sale_bill_id,
        		'customer_id'	=> $faker->randomElement($customers),
        		'shipper_id' => null,
        		'status_order' => 'pending',
        		'destination_address' =>$faker->address,
        		'ship_fee'	=> 2,
        		'delivery_date' => Carbon::now(),
        		'book_date'		=> Carbon::now()
        	];
        }

        foreach (array_chunk($sale_bills, 500) as $data) {
        	DB::table('sale_bills')->insert($data);
        }
    }
}
