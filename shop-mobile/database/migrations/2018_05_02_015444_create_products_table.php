<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateProductsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('products', function (Blueprint $table) {
            $table->increments('id');
            $table->string('product_name');
            $table->string('unit');
            $table->integer('base_price');
            $table->string('provider')->nullable();
            $table->string('description',500)->nullable();
            $table->integer('quantity')->nullable();
            $table->integer('product_type_id')->nullable();
            $table->string('product_trademark_id')->nullable();
            $table->string('main_image');
            $table->json('sub_images')->nullable();
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
        Schema::dropIfExists('products');
    }
}
