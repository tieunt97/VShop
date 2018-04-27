<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateKhachhangDanhgiaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('khachhang_danhgia', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('id_khach_hang');
            $table->integer('id_danh_gia');
            $table->timestamps();

            $table->index('id_khach_hang');
            $table->index('id_danh_gia');
            $table->unique(['id_khach_hang','id_danh_gia']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('khachhang_danhgia');
    }
}
