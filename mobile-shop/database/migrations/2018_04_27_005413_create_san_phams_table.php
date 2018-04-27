<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateSanPhamsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('san_phams', function (Blueprint $table) {
            $table->increments('id');
            $table->string('ten_sp');
            $table->string('don_vi_tinh');
            $table->integer('gia_chuan');
            $table->string('nha_sx')->nullable();
            $table->string('mo_ta',500)->nullable();
            $table->integer('so_luong')->nullable();
            $table->integer('id_loai_sp')->nullable();
            $table->string('thuong_hieu_sp')->nullable();
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
        Schema::dropIfExists('san_phams');
    }
}
