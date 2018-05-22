<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateSaleBillsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('sale_bills', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('customer_id')->unsigned();
            $table->integer('shipper_id')->unsigned()->nullable();
            $table->enum('status_order', ['pending','shipping','shipped','refunded','canceled'])->nullable();//trang thai don hang
            $table->dateTime('delivery_date')->nullable();
            $table->string('destination_address');
            $table->string('ship_fee');
            $table->dateTime('book_date');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('sale_bills');
    }
}
