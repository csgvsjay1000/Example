package ivg.cn.sharding.jdbc.springboot.example.config.tablesharding;

import java.util.Collection;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;

public class OrderTableShardingDBStrategy implements SingleKeyDatabaseShardingAlgorithm<String>{

	private int dbCount;
	
	@Override
	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
		for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue().hashCode() % dbCount + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames,
			ShardingValue<String> shardingValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
			ShardingValue<String> shardingValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getDbCount() {
		return dbCount;
	}

	public void setDbCount(int dbCount) {
		this.dbCount = dbCount;
	}

}
