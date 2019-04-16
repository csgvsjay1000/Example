package ivg.cn.sharding.jdbc.springboot.example.config.tablesharding;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

public class OrderTableSingleKeyShardingAlgorithm implements SingleKeyTableShardingAlgorithm<String>{

	private int tableCount = 1;
	
	private int dbCount = 1;
	
	
	@Override
	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
		for (String each : availableTargetNames) {
            if (each.endsWith((Math.abs(shardingValue.getValue().hashCode()) % (tableCount*dbCount))%tableCount + "")) {
                return each;
            }
        }
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames,
			ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<String> values = shardingValue.getValues();
        for (String value : values) {
            for (String tableNames : availableTargetNames) {
                if (tableNames.endsWith(value.hashCode() % tableCount + "")) {
                    result.add(tableNames);
                }
            }
        }
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
			ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        return result;
	}

	public int getTableCount() {
		return tableCount;
	}

	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}

	public int getDbCount() {
		return dbCount;
	}

	public void setDbCount(int dbCount) {
		this.dbCount = dbCount;
	}
	
}
