<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\VersionedModel;
class Config extends VersionedModel
{
    public $table = 'configs';

    // TODO: remove this ugly hack
    public static $tablename = 'configs';
    public static function getTableName() { return self::$tablename; }

    public $fillable = [
        'group',
        'key',
        'value',
        'display_name',
        'description',
        'input_type'
    ];

    /**
     * The attributes that should be casted to native types.
     *
     * @var array
     */
    protected $casts = [
        'id' => 'integer',
        'group' => 'string',
        'key' => 'string',
        'value' => 'string',
        'display_name' => 'string',
        'description' => 'string',
        'input_type' => 'integer'
    ];

    /**
     * Validation rules
     *
     * @var array
     */
    public static $rules = [];

    public static function getConfigByKey()
    {
        $configs = Config::getAll();
        return $configs->pluck('value', 'key');
    }

    public static function getConfigValue($key)
    {
        return self::findWhere('key', $key)->first();
    }
}
