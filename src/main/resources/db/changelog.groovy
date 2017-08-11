databaseChangeLog {
    changeSet(id: '1', author: 'dwu') {
        createTable(tableName: 'foobar') {
            column(name: 'id', type: 'int', autoIncrement: true) {
                constraints(primaryKey: true)
            }
            column(name: 'value', type: 'int')
        }
        rollback {
            dropTable(tableName: 'foobar')
        }
    }
}