# realm-copy-test
Compare Realm's writing speed between writing managed realm objects and copying un-managed realm objects.

### What to compare
- Managed: Edit directly on managed RealmObject in transaction
- Copying: Edit copied un-managed RealmObject and copy it into Realm in transaction

### Test cases
- Test01: Find RealmObject, edit 1 column, and write. 1,000 times.
- Test02(Big): Find RealmObject, edit 10 columns, and write. 1,000 times.


![Test Result](https://github.com/taycleed/realm-copy-test/blob/master/img/test_result.png)
