<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateChiTietNhapsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('chi_tiet_nhaps', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('so_hd')->unsigned();
            $table->integer('id_sp')->unsigned();
            $table->integer('don_gia')->unsigned()->nullable();
            $table->integer('so_luong')->unsigned()->nullable();
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
        Schema::dropIfExists('chi_tiet_nhaps');
    }
}
