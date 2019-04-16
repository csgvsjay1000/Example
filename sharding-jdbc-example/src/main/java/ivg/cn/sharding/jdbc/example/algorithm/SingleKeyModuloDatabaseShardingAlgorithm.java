package ivg.cn.sharding.jdbc.example.algorithm;

import java.util.Collection;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;

public class SingleKeyModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer>{

	@Override
	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
		
		return null;
	}

	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames,
			ShardingValue<Integer> shardingValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
			ShardingValue<Integer> shardingValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
